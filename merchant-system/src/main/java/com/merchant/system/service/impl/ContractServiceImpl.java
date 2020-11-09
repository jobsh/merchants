package com.merchant.system.service.impl;

import java.util.List;

import com.merchant.common.core.domain.entity.SysUser;
import com.merchant.common.utils.DateUtils;
import com.merchant.system.domain.bo.ContractBO;
import com.merchant.system.service.ISysUserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
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
    private ISysUserService sysUserService;


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
     * @param contractBO 合同
     * @return 结果
     */
    @Override
    public int updateContract(ContractBO contractBO)
    {
        return contractMapper.updateContract(contractBO);
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
    public int renew(Integer id) {

        Contract contract = contractMapper.selectContractById(id);
        ContractBO contractBO = new ContractBO();
        /**
         * if (旧合同到期) {
         * 	旧合同status = '到期续约';
         *     新增新合同；
         *     新合同status = '有效执行中';
         *     新合同类型为 '续签'
         * } else if(旧合同未到期) {
         *     旧合同status = '到期续约';
         *     新增新合同；
         *     新合同status = '有效执行中';
         *     新合同类型为 '续签'
         * }
         */
        BeanUtils.copyProperties(contract,contractBO);

        if (contract.getEndDate().after(DateUtils.getNowDate())) {
            contractBO.setStatus("到期解约");
        } else {
            contractBO.setStatus("未到期解约");
        }

        return contractMapper.updateContract(contractBO);
    }

    @Override
    public int transfer(Integer managerId) {
        // 根据phone查询出负责人
        SysUser sysUser = sysUserService.selectUserById(managerId.longValue());
        // 判断系统中是否有此负责人
        ContractBO contractBO = new ContractBO();
        contractBO.setManagerId(contractBO.getId());
        contractBO.setManager(sysUser.getUserName());
        contractBO.setSignUserId(contractBO.getId());
        contractBO.setSignUser(sysUser.getUserName());
        return contractMapper.updateContract(contractBO);
    }
}
