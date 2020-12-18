package com.merchant.system.service;

import java.util.List;
import com.merchant.system.domain.ContractFee;
import com.merchant.system.domain.bo.AddContractFeeBO;
import com.merchant.system.domain.bo.ContractFeeBO;
import com.merchant.system.domain.vo.ContractFeeVO;

/**
 * 费用管理Service接口
 * 
 * @author hanke
 * @date 2020-11-10
 */
public interface IContractFeeService 
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
     * @param contractFeeBO 费用管理
     * @return 费用管理集合
     */
    public List<ContractFee> selectContractFeeList(ContractFeeBO contractFeeBO);

    /**
     * 新增费用管理
     * 
     * @param contractFee 费用管理
     * @return 结果
     */
    public int insertContractFee(AddContractFeeBO contractFee);

    /**
     * 修改费用管理
     * 
     * @param contractFee 费用管理
     * @return 结果
     */
    public int updateContractFee(AddContractFeeBO contractFee);

    /**
     * 批量删除费用管理
     * 
     * @param ids 需要删除的费用管理ID
     * @return 结果
     */
    public int deleteContractFeeByIds(Long[] ids);

    /**
     * 删除费用管理信息
     * 
     * @param id 费用管理ID
     * @return 结果
     */
    public int deleteContractFeeById(Long id);

    /**
     * 根据合同编号查询合同费用信息
     * @param contractNum
     * @return
     */
    List<ContractFeeVO> getFeeByContractNum(String contractNum);

    /**
     * 根据费用编号审核费用
     * @param num
     * @return
     */
    int checkContractFeeByNum(String num, String checkDate);

    /**
     * 根据费用编号反审核费用
     * @param num
     * @return
     */
    int unCheckContractFeeByNum(String num);
}
