package com.merchant.system.mapper;

import com.merchant.system.domain.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname Workspace
 * @Description 工作台
 * @Date 2020/12/24 15:20
 * @Created by hanke
 */
public interface WorkspaceMapper {

    /**
     * 店面地图使用
     * @param type
     * @return
     */
    List<DianmianNumVO> selectDianmianNumList(@Param("type") String type);

    /**
     * 左上角店面和合同数据
     * @param type
     * @return
     */
    List<DianmianAndContractAllVO> dianmianAndContractAllList(@Param("type") String type);

    /**
     * 右侧区域加盟和单店加盟
     * @param type
     * @return
     */
    List<ContractDandianAndQuyuVO> selectContractDandianAndQuyuVO(@Param("type") String type);

    /**
     * 统计报表
     * @param type
     * @param deptId
     * @return
     */
    List<CustomerWorkSpaceVO> selectCustomerWorkspaceList(@Param("type") String type, @Param("deptId") Integer deptId);

    /**
     * 业绩报表
     * @param type
     * @param deptId
     * @return
     */
    List<CustomerFeeWorkSpaceVO> selectCustomerFeeWorkspaceList(@Param("type") String type,@Param("deptId") Integer deptId);
}
