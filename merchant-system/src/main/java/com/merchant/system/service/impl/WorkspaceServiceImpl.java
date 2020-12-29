package com.merchant.system.service.impl;

import com.merchant.system.domain.vo.*;
import com.merchant.system.mapper.WorkspaceMapper;
import com.merchant.system.service.IWorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname WorkspaceServiceImpl
 * @Description TODO
 * @Date 2020/12/24 16:34
 * @Created by hanke
 */
@Service
public class WorkspaceServiceImpl implements IWorkspaceService {

    @Autowired
    private WorkspaceMapper workspaceMapper;

    @Override
    public List<DianmianNumVO> selectDianmianNumList(String type) {
        return workspaceMapper.selectDianmianNumList(type);
    }

    @Override
    public List<DianmianAndContractAllVO> dianmianAndContractAllList(String type) {
        return workspaceMapper.dianmianAndContractAllList(type);
    }

    @Override
    public List<ContractDandianAndQuyuVO> selectContractDandianAndQuyuVO(String type) {
        return workspaceMapper.selectContractDandianAndQuyuVO(type);
    }

    @Override
    public List<CustomerWorkSpaceVO> selectCustomerWorkspaceList(String type, Integer deptId) {
        return workspaceMapper.selectCustomerWorkspaceList(type, deptId);
    }

    @Override
    public List<CustomerFeeWorkSpaceVO> selectCustomerFeeWorkspaceList(String type, Integer deptId) {
        return workspaceMapper.selectCustomerFeeWorkspaceList(type, deptId);
    }
}
