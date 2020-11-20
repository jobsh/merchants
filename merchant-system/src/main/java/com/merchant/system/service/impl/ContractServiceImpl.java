package com.merchant.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.merchant.common.annotation.ContractLog;
import com.merchant.common.annotation.Excel;
import com.merchant.common.config.MerchantConfig;
import com.merchant.common.core.domain.entity.SysUser;
import com.merchant.common.core.domain.model.LoginUser;
import com.merchant.common.enums.ContractOperType;
import com.merchant.common.enums.ContractStatus;
import com.merchant.common.enums.DianmianStatus;
import com.merchant.common.exception.BaseException;
import com.merchant.common.utils.DateUtils;
import com.merchant.common.utils.ServletUtils;
import com.merchant.common.utils.file.FileUploadUtils;
import com.merchant.common.utils.ip.IpUtils;
import com.merchant.system.domain.Contract;
import com.merchant.system.domain.ContractOperLog;
import com.merchant.system.domain.Dianmian;
import com.merchant.system.domain.bo.ContractBO;
import com.merchant.system.mapper.ContractMapper;
import com.merchant.system.service.IContractLogService;
import com.merchant.system.service.IContractService;
import com.merchant.system.service.IDianmianService;
import com.merchant.system.service.ISysUserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * 合同Service业务层处理
 * 
 * @author hanke
 * @date 2020-11-03
 */
@Service
public class ContractServiceImpl implements IContractService 
{
    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IContractLogService contractLogService;

    @Autowired
    private IDianmianService dianmianService;

    @Resource(name = "tokenServiceUtil")
    private TokenService tokenService;

    @Autowired
    private Sid sid;

    /**
     * 查询合同
     * 
     * @param id 合同ID
     * @return 合同
     */
    @Override
    public Contract selectContractById(Integer id)
    {
        return contractMapper.selectContractById(id);
    }

    @Override
    public List<Contract> selectContractByRootNum(String rootNum) {
        return contractMapper.selectContractByRootNum(rootNum);
    }

    /**
     * 查询合同列表
     * 
     * @param contractBO 合同
     * @return 合同
     */
    @Override
    public List<Contract> selectContractList(ContractBO contractBO)
    {
        return contractMapper.selectContractList(contractBO);
    }

    /**
     * 新增合同
     * 
     * @param contractBO 合同
     * @return 结果
     */
    @Override
    public int insertContract(ContractBO contractBO)
    {
        if (contractBO.getNum() == null) {
            contractBO.setNum(sid.nextShort());
        }
        int res = contractMapper.countContractByNum(contractBO.getNum());
        if (res > 0) {
            throw new BaseException("已存在该合同编号");
        }
        // 新签合同设置pid为0
        contractBO.setPid(0);
        // 新签合同rootNum设置为本合同编号，pid为0
        contractBO.setRootNum(contractBO.getNum());
        // 设置合同默认状态为有效执行中
        if (contractBO.getStatus() == null) {
            contractBO.setStatus(ContractStatus.EFFECTIVE_EXECUTING.getCode());
        }
        // type为新签合同
        contractBO.setType(ContractStatus.SIGN_NEW.getCode());
        // 审核状态为未审核
        contractBO.setCheckStatus(ContractStatus.UNCHECK.getCode());
        int result = contractMapper.insertContract(contractBO);
        if (result > 0) {
            // 添加合同日志
            ContractOperLog contractOperLog = new ContractOperLog();
            contractOperLog.setBusinessType(ContractOperType.ADD.ordinal());
            contractOperLog.setTitle("录入合同");
            this.setContractOperLog(contractOperLog);
            // 添加合同日志
            contractOperLog.setRequestMethod("POST");
            contractOperLog.setContractNum(contractBO.getNum());
            contractLogService.insertOperlog(contractOperLog);
        }
        return result;
    }

    /**
     * 修改合同
     * 
     * @param contractBO 合同
     * @return 结果
     */
    @Override
    public int updateContract(ContractBO contractBO) throws Exception {
        Contract oldContract = contractMapper.selectContractById(contractBO.getId());
        if (ContractStatus.CHECKED.getCode().equals(oldContract.getCheckDate())){
            return -1;
        }
        int res = contractMapper.updateContract(contractBO);
        // 记录日志
//        Map memberValues = this.getAnnotationMemberValues("updateContract", ContractLog.class);

        if (res > 0) {
            Contract newContract = contractMapper.selectContractById(contractBO.getId());
            Map<String, String> compareRes = compareTwoObject(oldContract, newContract);
            // 请求的地址
            ContractOperLog contractOperLog = new ContractOperLog();
            this.setContractOperLog(contractOperLog);
            // 添加合同日志
            contractOperLog.setRequestMethod("POST");
            contractOperLog.setContractNum(oldContract.getNum());
            contractOperLog.setBusinessType(ContractOperType.MODIFY.ordinal());
            contractOperLog.setTitle("修改合同");
            contractOperLog.setDescription(JSONObject.toJSONString(compareRes));
//            memberValues.put("description", compareRes);

            contractLogService.insertOperlog(contractOperLog);
        }
        return res;
    }

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的合同ID
     * @return 结果
     */
    @Override
    public int deleteContractByIds(Integer[] ids)
    {
        return contractMapper.deleteContractByIds(ids);
    }

    /**
     * 删除合同信息
     * 
     * @param id 合同ID
     * @return 结果
     */
    @Override
    public int deleteContractById(Integer id)
    {
        return contractMapper.deleteContractById(id);
    }

    @Override
    public List<Contract> selectContractListByCustomerId(Integer customerId) {
        ContractBO contractBO = new ContractBO();
        contractBO.setCustomerId(customerId);
        return contractMapper.selectContractList(contractBO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int terminate(MultipartFile file, ContractBO contractBO) throws IOException {
        Contract contract = contractMapper.selectContractById(contractBO.getId());
        ContractOperLog contractOperLog = new ContractOperLog();
        if (contract.getEndDate().after(DateUtils.getNowDate())) {
            contractBO.setStatus(ContractStatus.EXPIRED_TERMINATION.getCode());
            contractOperLog.setTitle("到期解约");
        } else {
            contractBO.setStatus(ContractStatus.UNEXPIRED_TERMINATION.getCode());
            contractOperLog.setTitle("未到期解约");
        }
        this.addSave(file, contractBO);
        int res = contractMapper.updateContract(contractBO);
        if (res > 0) {
            // 请求的地址
            this.setContractOperLog(contractOperLog);
            // 添加合同日志
            contractOperLog.setRequestMethod("POST");
            contractOperLog.setContractNum(contract.getNum());
            contractOperLog.setBusinessType(ContractOperType.TERMINATE.ordinal());
            contractOperLog.setDescription("解约时间：" + contractBO.getTerminateDate());

            contractLogService.insertOperlog(contractOperLog);

            Dianmian dianmian = new Dianmian();
            // 修改店面状态为闭店
            dianmian.setStatus(DianmianStatus.ClOSED.getCode());
            dianmianService.updateDianmianByContractNum(dianmian);
        }

        return res;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int renew(Integer id, ContractBO contractBO) {

        // 查询出要续签的合同
        Contract contract = contractMapper.selectContractById(id);
        if (ContractStatus.CHECKED.getCode().equals(contract.getCheckDate())){
            return -1;
        }
        ContractBO oldContract = new ContractBO();

        BeanUtils.copyProperties(contract,oldContract);
        if (contract.getEndDate().after(DateUtils.getNowDate())) {
            // 设置旧合同为到期解约
            oldContract.setStatus(ContractStatus.EXPIRED_TERMINATION.getCode());
        } else {
            // 设为未到期解约
            oldContract.setStatus(ContractStatus.UNEXPIRED_TERMINATION.getCode());
        }
        // 更新旧合同
        contractMapper.updateContract(oldContract);

        // 签发新合同
        contractBO.setRootNum(contract.getRootNum());
        // 设置新合同pid
        contractBO.setPid(contract.getId());
        // 设置新合同类型为续签
        contractBO.setType(ContractStatus.SIGN_RENEW.getCode());
        // 设置新合同状态为有效执行中
        contractBO.setStatus(ContractStatus.EFFECTIVE_EXECUTING.getCode());
        // 设置新合同审核状态为未审核
        contractBO.setCheckStatus(ContractStatus.UNCHECK.getCode());
        // 根据新合同编号查询是否存在该合同
        int res = contractMapper.insertContract(contractBO);
        if (res > 0) {
            // 请求的地址
            ContractOperLog contractOperLog = new ContractOperLog();
            this.setContractOperLog(contractOperLog);
            // 添加合同日志
            contractOperLog.setRequestMethod("POST");
            contractOperLog.setContractNum(contract.getNum());
            contractOperLog.setBusinessType(ContractOperType.RENEW.ordinal());
            contractOperLog.setTitle("合同续约");
            contractOperLog.setDescription("原合同：" + oldContract.getNum() + ";新合同：" + contractBO.getNum());
//            memberValues.put("description", compareRes);

            contractLogService.insertOperlog(contractOperLog);
        }

        return res;
    }

    @Override
    public int transfer(Integer id, Integer managerId) throws IllegalAccessException {
        // 根据phone查询出负责人 判断系统中是否有此负责人
        SysUser sysUser = sysUserService.selectUserById(managerId.longValue());
        Contract contract = contractMapper.selectContractById(id);
        ContractBO contractBO = new ContractBO();
        BeanUtils.copyProperties(contract, contractBO);
        contractBO.setManagerId(contractBO.getId());
        contractBO.setManager(sysUser.getUserName());
        contractBO.setSignUserId(contractBO.getId());
        contractBO.setSignUser(sysUser.getUserName());
        int res = contractMapper.updateContract(contractBO);
        // 查询出旧合同
        Contract oldContract = contractMapper.selectContractById(id);
        if (res > 0) {
            Contract newContract = contractMapper.selectContractById(contractBO.getId());
            Map<String, String> compareRes = compareTwoObject(oldContract, newContract);
            // 请求的地址
            ContractOperLog contractOperLog = new ContractOperLog();
            this.setContractOperLog(contractOperLog);
            // 添加合同日志
            contractOperLog.setRequestMethod("POST");
            contractOperLog.setContractNum(oldContract.getNum());
            contractOperLog.setBusinessType(ContractOperType.TRANSFER.ordinal());
            contractOperLog.setTitle("转移合同");
            contractOperLog.setDescription(JSONObject.toJSONString(compareRes));

            contractLogService.insertOperlog(contractOperLog);
        }
        return res;
    }

    @Override
    public int check(Integer id, String signDate) throws IllegalAccessException {
        ContractBO contractBO = new ContractBO();
        contractBO.setId(id);
        Contract contract = contractMapper.selectContractById(id);
        if (ContractStatus.CHECKED.getCode().equals(contract.getCheckDate())) {
            return -1;
        }
        if (contractBO.getSignDate() == null) {
            contractBO.setSignDate(signDate);
        }
        if (contractBO.getCheckDate() == null) {
            contractBO.setCheckDate(signDate);
        }
        // 设置状态为已审核
        contractBO.setCheckStatus(ContractStatus.CHECKED.getCode());

        // 查询出旧合同
        int res = contractMapper.updateContract(contractBO);
        if (res > 0) {
            Contract newContract = contractMapper.selectContractById(contractBO.getId());
            // 请求的地址
            ContractOperLog contractOperLog = new ContractOperLog();
            this.setContractOperLog(contractOperLog);
            // 添加合同日志
            contractOperLog.setRequestMethod("POST");
            contractOperLog.setContractNum(contractBO.getNum());
            contractOperLog.setBusinessType(ContractOperType.CHECK.ordinal());
            contractOperLog.setTitle("审核合同");
            contractOperLog.setDescription("合同状态改为已审核");

            contractLogService.insertOperlog(contractOperLog);
        }
        return res;
    }

    @Override
    public int uncheck(Integer id) {
        ContractBO contractBO = new ContractBO();
        Contract contract = contractMapper.selectContractById(id);
        if (ContractStatus.CHECKED.getCode().equals(contract.getCheckDate())) {
            return -1;
        }
        contractBO.setId(id);
        // 设置状态为未审核
        contractBO.setCheckStatus(ContractStatus.UNCHECK.getCode());
        contractBO.setCheckDate(null);
        int res = contractMapper.updateContract(contractBO);
        if (res > 0) {
            // 请求的地址
            ContractOperLog contractOperLog = new ContractOperLog();
            this.setContractOperLog(contractOperLog);
            // 添加合同日志
            contractOperLog.setRequestMethod("POST");
            contractOperLog.setContractNum(contractBO.getNum());
            contractOperLog.setBusinessType(ContractOperType.CHECK.ordinal());
            contractOperLog.setTitle("反审核合同");
            contractOperLog.setDescription("合同状态改为未审核");

            contractLogService.insertOperlog(contractOperLog);
        }
        return res;

    }

    @Override
    public int abandon(Integer id) {
        Contract contract = contractMapper.selectContractById(id);
        if (ContractStatus.CHECKED.getCode().equals(contract.getCheckDate())) {
            return -1;
        }
        ContractBO contractBO = new ContractBO();
        contractBO.setId(id);
        contractBO.setCheckStatus(ContractStatus.ABANDON.getCode());
        int res = contractMapper.updateContract(contractBO);
        if (res > 0) {
            // 请求的地址
            ContractOperLog contractOperLog = new ContractOperLog();
            this.setContractOperLog(contractOperLog);
            // 添加合同日志
            contractOperLog.setRequestMethod("GET");
            contractOperLog.setContractNum(contractBO.getNum());
            contractOperLog.setBusinessType(ContractOperType.CHECK.ordinal());
            contractOperLog.setTitle("合同失效");
            contractOperLog.setDescription("合同状态改为失效");

            contractLogService.insertOperlog(contractOperLog);
        }
        return res;
    }


    public static Map<String, String> compareTwoObject(Object obj1, Object obj2, String... ignoreFields) throws IllegalAccessException {
        Map<String, String> diffMap = new LinkedHashMap<>();
        List<String> ignoreFieldList = Arrays.asList(ignoreFields);
        Class<?> clazz1 = obj1.getClass();
        Class<?> clazz2 = obj2.getClass();
        Field[] fields1 = clazz1.getDeclaredFields();
        Field[] fields2 = clazz2.getDeclaredFields();
        BiPredicate biPredicate = (object1, object2) -> {
            if (object1 == null && object2 == null) {
                return true;
            }
            if (object1 == null && object2 != null){
                return false;
            }
            // 假如还有别的类型需要特殊判断 比如 BigDecimal, 演示，只写BigDecimal示例，其他都相似
            if (object1 instanceof BigDecimal && object2 instanceof BigDecimal) {
                if (object1 == null && object2 == null) {
                    return true;
                }
                if (object1 == null ^ object2 == null) {
                    return false;
                }
                return ((BigDecimal) object1).compareTo((BigDecimal) object2) == 0;
            }

            if (object1.equals(object2)) {
                return true;
            }
            return false;
        };

        for (Field field1 : fields1) {
            for (Field field2 : fields2) {
                if (ignoreFieldList.contains(field1.getName()) || ignoreFieldList.contains(field2.getName())) {
                    continue;
                }
                if (field1.getName().equals(field2.getName())) {
                    field1.setAccessible(true);
                    field2.setAccessible(true);
                    if (!biPredicate.test(field1.get(obj1), field2.get(obj2))) {
//                        diffMap.put("compare_object: ", obj1 + " vs " + obj2);
                        diffMap.put("修改前合同" + field1.getAnnotation(Excel.class).name(), field1.get(obj1).toString());
                        diffMap.put("修改后合同" + field2.getAnnotation(Excel.class).name(), field2.get(obj2).toString());
                    }
                }
            }
        }
        return diffMap;
    }

    private Map<String, Object> getAnnotationMemberValues(String methodName,Class clazz) throws Exception {
        ContractLog annotation = this.getClass().getMethod(methodName,clazz).getAnnotation(ContractLog.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        Field value = invocationHandler.getClass().getDeclaredField("memberValues");
        value.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
        return memberValues;
    }

    /**
     * 设置合同日志基础信息
     * @param contractOperLog 合同日志实体类
     */
    private void setContractOperLog(ContractOperLog contractOperLog) {
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        contractOperLog.setOperIp(ip);
        contractOperLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
        contractOperLog.setMethod(Thread.currentThread().getStackTrace()[2].getMethodName());
        if (loginUser != null)
        {
            contractOperLog.setOperName(loginUser.getUsername());
            contractOperLog.setDeptName(loginUser.getUser().getDept().getDeptName());
        }
    }

    /**
     * 合同附件上传
     */
    public void addSave(MultipartFile file, ContractBO contractBO) throws IOException {
        // 上传文件路径
        String filePath = MerchantConfig.getContractPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        contractBO.setFile(fileName);
    }

}
