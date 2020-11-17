package com.merchant.system.service;

import java.util.List;
import com.merchant.system.domain.Contract;
import com.merchant.system.domain.bo.ContractBO;
import io.swagger.models.auth.In;

/**
 * 合同Service接口
 * 
 * @author hanke
 * @date 2020-11-03
 */
public interface IContractService 
{
    /**
     * 查询合同
     * 
     * @param id 合同ID
     * @return 合同
     */
    public Contract selectContractById(Integer id);

    /**
     * 根据rootId查询关联合同
     * @param rootNum
     * @return
     */
    List<Contract> selectContractByRootNum(String rootNum);

    /**
     * 查询合同列表
     * 
     * @param contractBO 合同
     * @return 合同集合
     */
    public List<Contract> selectContractList(ContractBO contractBO);

    /**
     * 新增合同
     * 
     * @param contractBO 合同
     * @return 结果
     */
    public int insertContract(ContractBO contractBO);

    /**
     * 修改合同
     * 
     * @param contractBO 合同
     * @return 结果
     */
    public int updateContract(ContractBO contractBO) throws IllegalAccessException, NoSuchFieldException, Exception;

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的合同ID
     * @return 结果
     */
    public int deleteContractByIds(Integer[] ids);

    /**
     * 删除合同信息
     * 
     * @param id 合同ID
     * @return 结果
     */
    public int deleteContractById(Integer id);

    /**
     * 根据customerId查询合同list
     * @param customerId
     * @return
     */
    List<Contract> selectContractListByCustomerId(Integer customerId);

    /**
     * 根据合同id解约合同
     * @param id
     * @return
     */
    int terminate(Integer id);

    /**
     * 续签合同
     * @param contractBO
     * @return
     */
    int renew(Integer id, ContractBO contractBO);

    /**
     * 转移给手机号为phone的负责人
     * @param managerId
     * @return
     */
    int transfer(Integer id, Integer managerId);

    /**
     * 审核合同
     * @param id
     * @param signDate
     * @return
     */
    int check(Integer id, String signDate);


    /**
     * 反审核合同
     * @param id
     * @return
     */
    int uncheck(Integer id);
}
