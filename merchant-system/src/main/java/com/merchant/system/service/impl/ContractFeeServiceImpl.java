package com.merchant.system.service.impl;

import java.util.List;

import com.merchant.system.domain.bo.ContractFeeBO;
import com.merchant.system.domain.vo.ContractFeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.ContractFeeMapper;
import com.merchant.system.domain.ContractFee;
import com.merchant.system.service.IContractFeeService;

/**
 * 费用管理Service业务层处理
 * 
 * @author hanke
 * @date 2020-11-10
 */
@Service
public class ContractFeeServiceImpl implements IContractFeeService 
{
    @Autowired
    private ContractFeeMapper contractFeeMapper;

    /**
     * 查询费用管理
     * 
     * @param id 费用管理ID
     * @return 费用管理
     */
    @Override
    public ContractFee selectContractFeeById(Long id)
    {
        return contractFeeMapper.selectContractFeeById(id);
    }

    /**
     * 查询费用管理列表
     * 
     * @param contractFee 费用管理
     * @return 费用管理
     */
    @Override
    public List<ContractFee> selectContractFeeList(ContractFee contractFee)
    {
        return contractFeeMapper.selectContractFeeList(contractFee);
    }

    /**
     * 新增费用管理
     * 
     * @param contractFee 费用管理
     * @return 结果
     */
    @Override
    public int insertContractFee(ContractFee contractFee)
    {
        return contractFeeMapper.insertContractFee(contractFee);
    }

    /**
     * 修改费用管理
     * 
     * @param contractFee 费用管理
     * @return 结果
     */
    @Override
    public int updateContractFee(ContractFee contractFee)
    {
        return contractFeeMapper.updateContractFee(contractFee);
    }

    /**
     * 批量删除费用管理
     * 
     * @param ids 需要删除的费用管理ID
     * @return 结果
     */
    @Override
    public int deleteContractFeeByIds(Long[] ids)
    {
        return contractFeeMapper.deleteContractFeeByIds(ids);
    }

    /**
     * 删除费用管理信息
     * 
     * @param id 费用管理ID
     * @return 结果
     */
    @Override
    public int deleteContractFeeById(Long id)
    {
        return contractFeeMapper.deleteContractFeeById(id);
    }

    @Override
    public List<ContractFeeVO> getFeeByContractNum(String contractNum) {
        ContractFee contractFee = new ContractFee();
        contractFee.setContractNum(contractNum);
        return contractFeeMapper.selectContractFeeByContractNum(contractNum);
    }
}
