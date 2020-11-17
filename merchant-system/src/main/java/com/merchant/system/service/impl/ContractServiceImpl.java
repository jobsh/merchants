package com.merchant.system.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import com.alibaba.fastjson.JSONObject;
import com.merchant.common.annotation.ContractLog;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.entity.SysUser;
import com.merchant.common.enums.ContractOperType;
import com.merchant.common.enums.ContractStatus;
import com.merchant.common.exception.BaseException;
import com.merchant.common.utils.DateUtils;
import com.merchant.system.domain.ContractOperLog;
import com.merchant.system.domain.bo.ContractBO;
import com.merchant.system.service.IContractLogService;
import com.merchant.system.service.ISysUserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.ContractMapper;
import com.merchant.system.domain.Contract;
import com.merchant.system.service.IContractService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @ContractLog(title = "测试")
    public int updateContract(ContractBO contractBO) throws Exception {
        Contract oldContract = contractMapper.selectContractById(contractBO.getId());
        int res = contractMapper.updateContract(contractBO);
        // 记录日志
        ContractLog annotation = this.getClass().getMethod("updateContract",ContractBO.class).getAnnotation(ContractLog.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        Field value = invocationHandler.getClass().getDeclaredField("memberValues");
        value.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
        String description = (String) memberValues.get("description");

        if (res > 0) {
            Contract newContract = contractMapper.selectContractById(contractBO.getId());
            Map<String, String> compareRes = compareTwoObject(oldContract, newContract);
            // 添加合同日志
            ContractOperLog contractOperLog = new ContractOperLog();
            contractOperLog.setContractNum(oldContract.getNum());
            contractOperLog.setBusinessType(ContractOperType.MODIFY.ordinal());
            contractOperLog.setTitle("修改合同");
//            contractOperLog.setDescription(JSONObject.toJSONString(compareRes));
            memberValues.put("description", compareRes);

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
    public int terminate(Integer id) {
        Contract contract = contractMapper.selectContractById(id);
        ContractBO contractBO = new ContractBO();
        if (contract.getEndDate().after(DateUtils.getNowDate())) {
            contractBO.setStatus("到期解约");
        } else {
            contractBO.setStatus("未到期解约");
        }

        return contractMapper.updateContract(contractBO);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int renew(Integer id, ContractBO contractBO) {

        // 查询出要续签的合同
        Contract contract = contractMapper.selectContractById(id);
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

        return contractMapper.insertContract(contractBO);
    }

    @Override
    public int transfer(Integer id, Integer managerId) {
        // 根据phone查询出负责人 判断系统中是否有此负责人
        SysUser sysUser = sysUserService.selectUserById(managerId.longValue());
        Contract contract = contractMapper.selectContractById(id);
        ContractBO contractBO = new ContractBO();
        BeanUtils.copyProperties(contract, contractBO);
        contractBO.setManagerId(contractBO.getId());
        contractBO.setManager(sysUser.getUserName());
        contractBO.setSignUserId(contractBO.getId());
        contractBO.setSignUser(sysUser.getUserName());
        return contractMapper.updateContract(contractBO);
    }

    @Override
    public int check(Integer id, String signDate) {
        ContractBO contractBO = new ContractBO();
        contractBO.setId(id);
        if (contractBO.getSignDate() == null) {
            contractBO.setSignDate(signDate);
        }
        if (contractBO.getCheckDate() == null) {
            contractBO.setCheckDate(signDate);
        }
        // 设置状态为已审核
        contractBO.setCheckStatus(ContractStatus.CHECKED.getCode());
        return contractMapper.updateContract(contractBO);
    }

    @Override
    public int uncheck(Integer id) {
        ContractBO contractBO = new ContractBO();
        contractBO.setId(id);
        // 设置状态为未审核
        contractBO.setCheckStatus(ContractStatus.UNCHECK.getCode());
        contractBO.setCheckDate(null);
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
                        diffMap.put("修改前合同" + field1.getAnnotation(Excel.class).name(), field1.get(obj1).toString());
                        diffMap.put("修改后合同" + field2.getAnnotation(Excel.class).name(), field2.get(obj2).toString());
                    }
                }
            }
        }
        return diffMap;
    }

}
