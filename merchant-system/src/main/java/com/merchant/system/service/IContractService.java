package com.merchant.system.service;

import java.util.List;
import com.merchant.system.domain.Contract;

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
     * @param contract 合同
     * @return 合同集合
     */
    public List<Contract> selectContractList(Contract contract);

    /**
     * 新增合同
     * 
     * @param contract 合同
     * @return 结果
     */
    public int insertContract(Contract contract);

    /**
     * 修改合同
     * 
     * @param contract 合同
     * @return 结果
     */
    public int updateContract(Contract contract);

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
}
