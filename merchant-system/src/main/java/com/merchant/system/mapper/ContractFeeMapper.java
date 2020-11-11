package com.merchant.system.mapper;

import java.util.List;
import com.merchant.system.domain.ContractFee;

/**
 * 费用管理Mapper接口
 * 
 * @author hanke
 * @date 2020-11-10
 */
public interface ContractFeeMapper 
{
    /**
     * 查询费用管理
     * 
     * @param id 费用管理ID
     * @return 费用管理
     */
    public ContractFee selectContractFeeById(Long id);

    /**
     * 查询费用管理列表
     * 
     * @param contractFee 费用管理
     * @return 费用管理集合
     */
    public List<ContractFee> selectContractFeeList(ContractFee contractFee);

    /**
     * 新增费用管理
     * 
     * @param contractFee 费用管理
     * @return 结果
     */
    public int insertContractFee(ContractFee contractFee);

    /**
     * 修改费用管理
     * 
     * @param contractFee 费用管理
     * @return 结果
     */
    public int updateContractFee(ContractFee contractFee);

    /**
     * 删除费用管理
     * 
     * @param id 费用管理ID
     * @return 结果
     */
    public int deleteContractFeeById(Long id);

    /**
     * 批量删除费用管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteContractFeeByIds(Long[] ids);
}
