package com.merchant.system.service.impl;

import java.util.List;

import com.merchant.common.core.domain.entity.SysUser;
import com.merchant.common.enums.ContractStatus;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Contract> selectContractByRootNum(String rootNum) {
        return contractMapper.selectContractByRootNum(rootNum);
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
        if (contractBO.getNum() == null) {
            contractBO.setNum(sid.nextShort());
        }
        // 新签合同rootNum设置为本合同编号，pid为0
        contractBO.setRootNum(contractBO.getNum());
        // 设置合同默认状态为有效执行中
        if (contractBO.getStatus() == null) {
            contractBO.setStatus(ContractStatus.EFFECTIVE_EXECUTING.getCode());
        }
        // type为新签合同
        contractBO.setType(ContractStatus.SIGN_NEW.getCode());
        contractBO.setPid(0);

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
    @Transactional(propagation = Propagation.REQUIRED)
    public int renew(Integer id, ContractBO contractBO) {

        // 查询出要续签的合同
        Contract contract = contractMapper.selectContractById(id);
        ContractBO oldContract = new ContractBO();

        BeanUtils.copyProperties(contract,oldContract);
        if (contract.getEndDate().after(DateUtils.getNowDate())) {
            // 设置旧合同为到期解约
            oldContract.setStatus(ContractStatus.EXPIRED_TERMINATION.getCode());
        } else {
            oldContract.setStatus("未到期解约");
        }
        // 更新旧合同
        contractMapper.updateContract(oldContract);

        // 签发新合同
        contractBO.setRootNum(contract.getRootNum());
        // 设置新合同pid
        contractBO.setPid(contract.getId());
        // 设置新合同类型为续签
        contractBO.setType(ContractStatus.SIGN_RENEW.getCode());
        // 设置新合同状态为有效执行中
        contractBO.setStatus(ContractStatus.EFFECTIVE_EXECUTING.getCode());
        // 设置新合同审核状态为未审核
        contractBO.setCheckStatus(ContractStatus.UNCHECK.getCode());
        return contractMapper.insertContract(contractBO);
    }

    @Override
    public int transfer(Integer managerId) {
        // 根据phone查询出负责人 判断系统中是否有此负责人
        SysUser sysUser = sysUserService.selectUserById(managerId.longValue());
        ContractBO contractBO = new ContractBO();
        contractBO.setManagerId(contractBO.getId());
        contractBO.setManager(sysUser.getUserName());
        contractBO.setSignUserId(contractBO.getId());
        contractBO.setSignUser(sysUser.getUserName());
        return contractMapper.updateContract(contractBO);
    }

    @Override
    public int check(Integer id, String signDate) {
        ContractBO contractBO = new ContractBO();
        contractBO.setId(id);
        contractBO.setSignDate(signDate);
        // 设置状态为已审核
        contractBO.setCheckStatus(ContractStatus.CHECKED.getCode());
        contractBO.setCheckDate(DateUtils.dateTimeNow());
        return contractMapper.updateContract(contractBO);
    }

    @Override
    public int check(Integer id) {
        ContractBO contractBO = new ContractBO();
        contractBO.setId(id);
        // 设置状态为未审核
        contractBO.setCheckStatus(ContractStatus.UNCHECK.getCode());
        contractBO.setCheckDate(null);
        return contractMapper.updateContract(contractBO);
    }
}
