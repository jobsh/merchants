package com.merchant.system.service.impl;

import java.util.List;

import com.merchant.common.annotation.DataScope;
import com.merchant.common.enums.ContractFeeStatus;
import com.merchant.common.utils.DateUtils;
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
    @DataScope(deptAlias = "d", userAlias = "u")
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
    @DataScope(deptAlias = "d", userAlias = "u")
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
        ContractFee contractFee1 = contractFeeMapper.selectContractFeeById(contractFee.getId());
        // 如果是已审核状态，则不可编辑
        if (ContractFeeStatus.CHECKED.getCode().equals(contractFee1.getCheckStatus())) {
            return -1;
        }
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
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<ContractFeeVO> getFeeByContractNum(String contractNum) {
        ContractFee contractFee = new ContractFee();
        contractFee.setContractNum(contractNum);
        return contractFeeMapper.selectContractFeeByContractNum(contractNum);
    }

    @Override
    public int checkContractFeeByNum(String num, String checkDate) {
        ContractFeeBO contractFeeBO = new ContractFeeBO();
        contractFeeBO.setNum(num);
        contractFeeBO.setCheckStatus(ContractFeeStatus.CHECKED.getCode());
        contractFeeBO.setCheckDate(DateUtils.parseDate(checkDate));

        return contractFeeMapper.updateContractFeeByNum(contractFeeBO);
    }

    @Override
    public int unCheckContractFeeByNum(String num) {
        ContractFeeBO contractFeeBO = new ContractFeeBO();
        contractFeeBO.setNum(num);
        contractFeeBO.setCheckStatus(ContractFeeStatus.UNCHECK.getCode());

        return contractFeeMapper.updateContractFeeByNum(contractFeeBO);
    }
}
