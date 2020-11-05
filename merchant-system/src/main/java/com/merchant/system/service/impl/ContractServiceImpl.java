package com.merchant.system.service.impl;

import java.util.List;

import com.merchant.common.utils.DateUtils;
import com.merchant.system.domain.bo.ContractBO;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.ContractMapper;
import com.merchant.system.domain.Contract;
import com.merchant.system.service.IContractService;

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
        String contractNum = sid.nextShort();
        contractBO.setNum(contractNum);
        return contractMapper.insertContract(contractBO);
    }

    /**
     * 修改合同
     * 
     * @param contract 合同
     * @return 结果
     */
    @Override
    public int updateContract(Contract contract)
    {
        return contractMapper.updateContract(contract);
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
}
