package com.merchant.system.service;

import com.merchant.system.domain.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取某公司某月的合同签约量
     * @param companyId
     * @param month
     * @return
     */
    Integer selectQyNumByCompanyId(Integer companyId,Integer month);

    /**
     * 获取每个公司的某个月的合同签约量
     * @param month
     * @return
     */
    Map<String, Integer> selectQyNumAll(Integer month);

    Map<String, Integer> selectQyNumAllOfQuarter();

    Map<String, Integer> selectQyNumAllOfYear();
}
