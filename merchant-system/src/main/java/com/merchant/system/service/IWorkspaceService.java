package com.merchant.system.service;

import com.merchant.system.domain.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname Workspace
 * @Description TODO
 * @Date 2020/12/24 15:20
 * @Created by hanke
 */
public interface IWorkspaceService {

    List<DianmianNumVO> selectDianmianNumList(String type);

    List<DianmianAndContractAllVO> dianmianAndContractAllList(String type);

    List<ContractDandianAndQuyuVO> selectContractDandianAndQuyuVO(String type);

    List<CustomerWorkSpaceVO> selectCustomerWorkspaceList(String type, Integer deptId);

    List<CustomerFeeWorkSpaceVO> selectCustomerFeeWorkspaceList(String type, Integer deptId);
}
