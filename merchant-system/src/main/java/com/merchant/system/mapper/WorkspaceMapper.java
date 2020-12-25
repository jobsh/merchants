package com.merchant.system.mapper;

import com.merchant.system.domain.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname Workspace
 * @Description TODO
 * @Date 2020/12/24 15:20
 * @Created by hanke
 */
public interface WorkspaceMapper {

    List<DianmianNumVO> selectDianmianNumList(@Param("type") String type);

    List<DianmianAndContractAllVO> dianmianAndContractAllList(@Param("type") String type);

    List<ContractDandianAndQuyuVO> selectContractDandianAndQuyuVO(@Param("type") String type);

    List<CustomerWorkSpaceVO> selectCustomerWorkspaceList(@Param("type") String type);

    List<CustomerFeeWorkSpaceVO> selectCustomerFeeWorkspaceList(@Param("type") String type);
}
