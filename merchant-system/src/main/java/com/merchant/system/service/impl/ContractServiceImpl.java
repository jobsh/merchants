package com.merchant.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.merchant.common.annotation.ContractLog;
import com.merchant.common.annotation.DataScope;
import com.merchant.common.annotation.Excel;
import com.merchant.common.config.MerchantConfig;
import com.merchant.common.core.domain.entity.SysUser;
import com.merchant.common.core.domain.model.LoginUser;
import com.merchant.common.enums.ContractOperType;
import com.merchant.common.enums.ContractStatus;
import com.merchant.common.enums.GenjinStatus;
import com.merchant.common.exception.BaseException;
import com.merchant.common.exception.CustomException;
import com.merchant.common.utils.DateUtils;
import com.merchant.common.utils.JsonUtils;
import com.merchant.common.utils.ServletUtils;
import com.merchant.common.utils.file.FileUploadUtils;
import com.merchant.common.utils.ip.IpUtils;
import com.merchant.system.domain.*;
import com.merchant.system.domain.bo.*;
import com.merchant.system.domain.vo.DianmianVO;
import com.merchant.system.mapper.ContractMapper;
import com.merchant.system.service.*;
import com.qiniu.util.Json;
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
import java.util.*;
import java.util.function.BiPredicate;

import static com.merchant.common.constant.Constants.CONTRACT_PREFIX;

/**
 * 合同Service业务层处理
 * 
 * @author hanke
 * @date 2020-11-03
 */
@Service
public class ContractServiceImpl implements IContractService 
{
    @Resource
    private ContractMapper contractMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ICustomerService customerService;

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
     * @param contractParamBO 合同
     * @return 合同
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Contract> selectContractList(ContractParamBO contractParamBO)
    {
        return contractMapper.selectContractList(contractParamBO);
    }

    /**
     * 新增合同
     * 
     * @param contractBO 合同
     * @return 结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertContract(AddContractBO contractBO)
    {
        contractBO.setNum(CONTRACT_PREFIX + sid.nextShort());
        int res = contractMapper.countContractByCode(contractBO.getCode());
        if (res > 0) {
            throw new BaseException("已存在该合同编号");
        }

        // 新签合同设置pid为0
        contractBO.setPid(0);
        // 新签合同rootNum设置为本合同编号，pid为0
        contractBO.setRootNum(contractBO.getNum());
        // 创建人
        String createBy = tokenService.getLoginUser(ServletUtils.getRequest()).getUsername();
        contractBO.setCreateBy(createBy);
        Date now = DateUtils.getNowDate();
        Date start = DateUtils.parseDate(contractBO.getBeginDate());
        Date end = DateUtils.parseDate(contractBO.getEndDate());
        boolean isIn = DateUtils.isInTimeLine(now, start, end);

        if (now.before(start)) {
            // 如果当前时间在合同开始时间之前，设置合同状态为有效未执行
            contractBO.setStatus(ContractStatus.EFFECTIVE_NOT_EXECUTE.getCode());
        }
        if (isIn) {
            contractBO.setStatus(ContractStatus.EFFECTIVE_EXECUTING.getCode());
        }
        else {
            contractBO.setStatus(ContractStatus.EXPIRED.getCode());
        }
        // type为新签合同
        contractBO.setType(ContractStatus.SIGN_NEW.getCode());
        // 审核状态为未审核
        contractBO.setCheckStatus(ContractStatus.UNCHECK.getCode());
        Fee fee = JSONObject.parseObject(contractBO.getFee(), Fee.class);
        JingyingManagerFee jingyingManagerFee = fee.getJingyingManagerFee();
        if (jingyingManagerFee == null) {
            throw new CustomException("经营管理费不能为空");
        }
        Integer total = jingyingManagerFee.getDetail().stream().mapToInt(item -> Integer.parseInt(item.get("money"))).sum();
        jingyingManagerFee.setTotal(total.toString());
        fee.setJingyingManagerFee(jingyingManagerFee);
        System.out.println("---------------fee:" + JSONObject.toJSONString(fee));
        contractBO.setFee(JSONObject.toJSONString(fee));
        int result = contractMapper.insertContract(contractBO);
        if (result > 0) {
            // 更新合同状态
            ContractBO contractBO1 = new ContractBO();
            BeanUtils.copyProperties(contractBO,contractBO1);
            this.updateThisContractStatus(contractBO1);

            // 客户录入合同后自动变为成交状态
            CustomerBO customerBO = new CustomerBO();
            customerBO.setId(contractBO.getCustomerId());
            // 跟进状态改为成交状态
            customerBO.setGenjinStatus(GenjinStatus.DEAL.getCode());
            customerBO.setGenjinDate(DateUtils.getDate());
            customerService.updateCustomer(customerBO);

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
        if (ContractStatus.CHECKED.getCode().equals(oldContract.getCheckStatus())){
            return -1;
        }

        ContractCompareBO oldContactBO = new ContractCompareBO();
        ContractCompareBO newContactBO = new ContractCompareBO();
        BeanUtils.copyProperties(oldContract,oldContactBO);
        BeanUtils.copyProperties(contractBO,newContactBO);
        // 进行时间转化
        oldContactBO.setBeginDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,oldContract.getBeginDate()));
        oldContactBO.setEndDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,oldContract.getEndDate()));
        oldContactBO.setSignDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,oldContract.getSignDate()));

        Fee oldFee = JSONObject.parseObject(oldContract.getFee(), Fee.class);
        Fee newFee = JSONObject.parseObject(contractBO.getFee(), Fee.class);
        JingyingManagerFee oldManagerFee = oldFee.getJingyingManagerFee();
        JingyingManagerFee newManagerFee = newFee.getJingyingManagerFee();
        // 计算经营管理费total
        // Integer total = newManagerFee.getDetail().stream().mapToInt(item -> Integer.parseInt(item.get("money"))).reduce(Integer::sum).orElse(0);
        Integer total = newManagerFee.getDetail().stream().mapToInt(item -> Integer.parseInt(item.get("money"))).sum();
        newManagerFee.setTotal(total + "");
        contractBO.setFee(JSON.toJSONString(newFee));
        int res = contractMapper.updateContract(contractBO);
        newFee.setJingyingManagerFee(newManagerFee);
        contractBO.setFee(JSON.toJSONString(newFee));
        // 改成元为单位
        this.feeToYuan(oldFee);
        this.feeToYuan(newFee);
        this.managerFeeToYuan(newManagerFee);
        this.managerFeeToYuan(oldManagerFee);

        // 记录日志
//        Map memberValues = this.getAnnotationMemberValues("updateContract", ContractLog.class);

        if (res > 0) {
// 更新合同状态
            ContractBO contractBO1 = new ContractBO();
            BeanUtils.copyProperties(contractBO,contractBO1);
            this.updateThisContractStatus(contractBO1);

            Map<String,Object> finalResultMap = new HashMap<>();
            Map<String, String> compareRes = compareTwoObject(oldContactBO, newContactBO);
            Map<String, String> compareFee = compareTwoObject(oldFee, newFee,"jingyingManagerFee");
            Map<String, String> compareManagerFee = compareTwoObject(oldManagerFee, newManagerFee);

            if (compareRes!= null && compareRes.size() > 0 && !compareRes.isEmpty()) {
                finalResultMap.put("基础内容:",compareRes);
            }
            if (compareFee!= null && compareFee.size() > 0 && !compareFee.isEmpty()) {
                finalResultMap.put("费用详情:",compareFee);
            }
            if (compareManagerFee!= null && compareManagerFee.size() > 0 && !compareManagerFee.isEmpty()) {
                finalResultMap.put("经营管理费:",compareManagerFee);
            }
            // 请求的地址
            ContractOperLog contractOperLog = new ContractOperLog();
            if (finalResultMap!= null && finalResultMap.size() > 0 && !finalResultMap.isEmpty()) {
                this.setContractOperLog(contractOperLog);
                // 添加合同日志
                contractOperLog.setRequestMethod("PUT");
                contractOperLog.setContractNum(oldContract.getNum());
                contractOperLog.setBusinessType(ContractOperType.MODIFY.ordinal());
                contractOperLog.setTitle("修改合同");
                contractOperLog.setDescription(JSON.toJSONString(finalResultMap));
                contractLogService.insertOperlog(contractOperLog);
            }
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
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Contract> selectContractListByCustomerId(Integer customerId) {
        ContractParamBO contractParamBO = new ContractParamBO();
        contractParamBO.setCustomerId(customerId);
        return contractMapper.selectContractList(contractParamBO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int terminate(ContractBO contractBO) {
        Contract contract = contractMapper.selectContractById(contractBO.getId());
        ContractOperLog contractOperLog = new ContractOperLog();
        if (contract.getEndDate().after(DateUtils.parseDate(contractBO.getTerminateDate()))) {
            contractBO.setStatus(ContractStatus.UNEXPIRED_TERMINATION.getCode());
            contractOperLog.setTitle("未到期解约");
        } else {
            contractBO.setStatus(ContractStatus.EXPIRED_TERMINATION.getCode());
            contractOperLog.setTitle("到期解约");
        }
//        this.addSave(file, contractBO); 改为七牛云
        int res = contractMapper.updateContract(contractBO);

        //  如果合同状态都是解约状态，则客户跟进状态自动为解约
        if (res > 0) {
            // 请求的地址
            this.setContractOperLog(contractOperLog);
            // 添加合同日志
            contractOperLog.setRequestMethod("POST");
            contractOperLog.setContractNum(contract.getNum());
            contractOperLog.setBusinessType(ContractOperType.TERMINATE.ordinal());
            contractLogService.insertOperlog(contractOperLog);

//            Dianmian dianmian = new Dianmian();
//            // 修改店面状态为闭店
//            dianmian.setStatus(DianmianStatus.ClOSED.getCode());
//            dianmianService.updateDianmianByContractNum(dianmian);
        }

        return res;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int renew(Integer id, AddContractBO contractBO) {

        // 查询出要续签的合同
        Contract contract = contractMapper.selectContractById(id);
        if (ContractStatus.CHECKED.getCode().equals(contract.getCheckStatus())){
            return -1;
        }
        ContractBO oldContract = new ContractBO();

        BeanUtils.copyProperties(contract,oldContract);
        // 无论旧合同此时是否到期，只要续约旧合同都改为到期续约
        oldContract.setStatus(ContractStatus.EXPIRED_RENEW.getCode());
        // 更新旧合同
        contractMapper.updateContract(oldContract);

        // 签发新合同
        if (contractMapper.countContractByCode(contractBO.getCode()) > 0) {
            throw new BaseException("已存在该合同编号");
        }
        List<DianmianVO> dianmianList = dianmianService.selectDianmianByContractNum(contract.getNum());

        contractBO.setNum(CONTRACT_PREFIX + sid.nextShort());
        contractBO.setRootNum(contract.getRootNum());
        // 设置新合同pid
        contractBO.setPid(contract.getId());
        // 设置新合同类型为续签
        contractBO.setType(ContractStatus.SIGN_RENEW.getCode());
        // 设置新合同状态为有效执行中
        // 如果续约时间和合同开始时间相同则直接变为有效执行中，如果合同开始时间在续约时间之后为有效未执行，
        // TODO 当续约的新合同到了合同开始日期时要自动变成有效执行中状态
        if (DateUtils.getDate().equals(contractBO.getBeginDate())) {
            // 如果续约时间和合同开始时间相同则直接变为有效执行中
            contractBO.setStatus(ContractStatus.EFFECTIVE_EXECUTING.getCode());
        } else {
            contractBO.setStatus(ContractStatus.EFFECTIVE_NOT_EXECUTE.getCode());
        }
        // 设置新合同审核状态为未审核
        contractBO.setCheckStatus(ContractStatus.UNCHECK.getCode());
        // 增加新合同记录
        contractBO.setDianmianNum(dianmianList.size());
        int res = contractMapper.insertContract(contractBO);
        if (res > 0) {
            ContractOperLog contractOperLog = new ContractOperLog();
            this.setContractOperLog(contractOperLog);
            // 添加合同日志
            contractOperLog.setRequestMethod("POST");
            contractOperLog.setContractNum(contract.getNum());
            contractOperLog.setBusinessType(ContractOperType.RENEW.ordinal());
            contractOperLog.setTitle("合同续约");
            contractOperLog.setDescription("原合同：" + oldContract.getNum() + ";新合同：" + contractBO.getNum());
            contractLogService.insertOperlog(contractOperLog);

            // 修改店面对应的最新合同
            dianmianList.forEach(t -> {
                t.setContractNum(contractBO.getNum());
                dianmianService.updateDianmian(t);
            });
        }

        return res;
    }

    @Override
    public int transfer(Integer[] ids, Integer managerId) throws IllegalAccessException {
        // 根据phone查询出负责人 判断系统中是否有此负责人
//        SysUser sysUser = sysUserService.selectUserByPhone(phone);
        SysUser sysUser = sysUserService.selectUserById(managerId.longValue());
        List<Contract> contractList = contractMapper.selectContractByIds(ids);
        int updateNum = 0;
        for (Contract contract : contractList) {
            ContractBO contractBO = new ContractBO();
            BeanUtils.copyProperties(contract, contractBO);
            contractBO.setManagerId(managerId);
            contractBO.setManager(sysUser.getUserName());
            contractBO.setSignUserId(managerId);
            contractBO.setSignUser(sysUser.getUserName());
            int res = contractMapper.updateContract(contractBO);
            // 查询出旧合同
            Contract oldContract = contractMapper.selectContractById(contract.getId());
            if (res > 0) {
                updateNum++;
                Contract newContract = contractMapper.selectContractById(contractBO.getId());
//                Map<String, String> compareRes = compareTwoObject(oldContract, newContract);
                // 请求的地址
                ContractOperLog contractOperLog = new ContractOperLog();
                this.setContractOperLog(contractOperLog);
                // 添加合同日志
                contractOperLog.setRequestMethod("POST");
                contractOperLog.setContractNum(oldContract.getNum());
                contractOperLog.setBusinessType(ContractOperType.TRANSFER.ordinal());
                contractOperLog.setTitle("合同由" + contract.getManager() + "转移给" + sysUser.getUserName());
//                contractOperLog.setDescription(JSONObject.toJSONString(compareRes));

                contractLogService.insertOperlog(contractOperLog);
            }
        }

        return updateNum;
    }

    @Override
    public int check(Integer id, String checkDate) {
        ContractBO contractBO = new ContractBO();
        contractBO.setId(id);
        Contract contract = contractMapper.selectContractById(id);
        if (ContractStatus.CHECKED.getCode().equals(contract.getCheckStatus())) {
            return -1;
        }
        contractBO.setCheckDate(checkDate);
        contractBO.setCheckDate(DateUtils.dateTimeNow());
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

    @Override
    public int uploadContractFile(Integer id, String files) {
        Contract contract = contractMapper.selectContractById(id);
        ContractBO contractBO = new ContractBO();
        BeanUtils.copyProperties(contract, contractBO);
        contractBO.setFile(files);
        return contractMapper.updateContract(contractBO);
    }

    @Override
    public int uploadContractImgs(ContractBO contractBO) {
        return contractMapper.updateContract(contractBO);
    }

    @Override
    public Contract selectContractByNum(String num) {
        return contractMapper.selectContractByNum(num);
    }

    @Override
    public void autoExpire(ContractBO contractBO) {
        contractMapper.autoExpire(contractBO);
    }

    @Override
    public void autoBegin(ContractBO contractBO) {
        contractMapper.autoBegin(contractBO);
    }

    @Override
    public int existCode(String code) {
        return contractMapper.countContractByCode(code);
    }

    @Override
    public int updateThisContractStatus(ContractBO contractBO) {
        Date nowDate = DateUtils.getNowDate();
        String beginDate = contractBO.getBeginDate();
        String endDate = contractBO.getEndDate();
        if (DateUtils.parseDate(beginDate).before(nowDate) && DateUtils.parseDate(endDate).after(nowDate)) {
            // 有效执行中
            contractBO.setStatus(ContractStatus.EFFECTIVE_EXECUTING.getCode());
        }
        if (DateUtils.parseDate(beginDate).after(nowDate) && DateUtils.parseDate(endDate).after(nowDate)) {
            contractBO.setStatus(ContractStatus.EFFECTIVE_NOT_EXECUTE.getCode());
        }
        return contractMapper.updateContract(contractBO);
    }

    @Override
    public int updateTerminateContract(ContractBO contractBO) {
        return contractMapper.updateContract(contractBO);
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
                        diffMap.put(field1.getAnnotation(Excel.class).name(), field1.get(obj1).toString() + "=>" + field2.get(obj2).toString());
//                        diffMap.put("修改后合同" + field2.getAnnotation(Excel.class).name(), field2.get(obj2).toString());
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
     * 合同解约附件上传
     */
    public void addSave(MultipartFile file, ContractBO contractBO) throws IOException {
        // 上传文件路径-
        String filePath = MerchantConfig.getContractPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        contractBO.setTerminateFile(fileName);
    }

    private Fee feeToYuan(Fee fee){
        // 改成元为单位
        fee.setLvyueFee((Integer.parseInt(fee.getLvyueFee()) / 100) + "元");
        fee.setYunyingManagerFee((Integer.parseInt(fee.getYunyingManagerFee()) / 100) + "元");
        fee.setSystemUseFee((Integer.parseInt(fee.getSystemUseFee()) / 100) + "元");
        fee.setSystemMaintenanceFee((Integer.parseInt(fee.getSystemMaintenanceFee()) / 100) + "元");
        fee.setDaibanFee((Integer.parseInt(fee.getDaibanFee()) / 100) + "元");
        fee.setGuohuoFee((Integer.parseInt(fee.getGuohuoFee()) / 100) + "元");
        return fee;
    }
    private void managerFeeToYuan(JingyingManagerFee managerFee) {
        // 改成元为单位
        managerFee.setTotal(Integer.parseInt(managerFee.getTotal()) / 100 + "元");
        for (Map<String, String> detail : managerFee.getDetail()) {
            for (Iterator iter = detail.keySet().iterator(); iter.hasNext(); ) {
                String key = (String) iter.next();
                if (key == "money") {
                    detail.put(key, (Integer.parseInt(detail.get(key)) / 100) + "元");
                }
            }
        }
    }



}
