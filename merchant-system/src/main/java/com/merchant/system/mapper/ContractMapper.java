package com.merchant.system.mapper;

import java.util.List;
import com.merchant.system.domain.Contract;
import com.merchant.system.domain.bo.AddContractBO;
import com.merchant.system.domain.bo.ContractBO;

/**
 * 合同Mapper接口
 * 
 * @author hanke
 * @date 2020-11-03
 */
public interface ContractMapper 
{
    /**
     * 查询合同
     * 
     * @param id 合同ID
     * @return 合同
     */
    public Contract selectContractById(Integer id);

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
    public int insertContract(AddContractBO contractBO);

    /**
     * 修改合同
     * 
     * @param contractBO 合同
     * @return 结果
     */
    public int updateContract(ContractBO contractBO);

    /**
     * 删除合同
     * 
     * @param id 合同ID
     * @return 结果
     */
    public int deleteContractById(Integer id);

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteContractByIds(Integer[] ids);

    /**
     * 根据合同编号查询合同
     * @param num
     * @return
     */
    Contract selectContractByNum(String num);

    /**
     * 根据合同编号查询有没有重复编号合同
     * @param num
     * @return
     */
    int countContractByNum(String num);

    List<Contract> selectContractByIds(Integer[] ids);

    int autoExpire(ContractBO contractBO);

    int autoBegin(ContractBO contractBO);
}
