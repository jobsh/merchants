package com.merchant.system.service;

import com.merchant.system.domain.vo.ContractDandianAndQuyuVO;
import com.merchant.system.domain.vo.DianmianAndContractAllVO;
import com.merchant.system.domain.vo.DianmianNumVO;

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
}
