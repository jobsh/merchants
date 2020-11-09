package com.merchant.system.service;

import java.util.List;
import com.merchant.system.domain.Contract;
import com.merchant.system.domain.bo.ContractBO;

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
     * @param contract 合同
     * @return 结果
     */
    public int updateContract(ContractBO contractBO);

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
     * 根据合同id续约合同
     * @param id
     * @return
     */
    int renew(Integer id);

    /**
     * 转移给手机号为phone的负责人
     * @param phone
     * @return
     */
    int transfer(Integer managerId);
}
