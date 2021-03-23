package com.merchant.system.domain.vo;

import lombok.Data;

/**
 * @Classname ContractDandianAndQuyuVO
 * @Description TODO
 * @Date 2020/12/24 16:07
 * @Created by hanke
 */
@Data
public class ContractDandianAndQuyuVO {
    private String deptName;
    /** 单店数量 */
    private Integer dandianNum;
    /** 区域店面数量 */
    private Integer quyuDianmianNum;
    /** 区域合同数量 */
    private Integer quyuContractNum;
}
