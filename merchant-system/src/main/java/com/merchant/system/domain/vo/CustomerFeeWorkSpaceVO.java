package com.merchant.system.domain.vo;

import com.merchant.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @Classname CustomerFeeReportVO
 * @Description TODO
 * @Date 2020/12/2 11:43
 * @Created by hanke
 */
@Data
public class CustomerFeeWorkSpaceVO extends BaseEntity {

    private Integer lvyueFee;
    private Integer jingyingManagerFee;
    private Integer yunyingManagerFee;
    private Integer systemUseFee;
    private Integer systemMaintenanceFee;
    private Integer daibanFee;
    private Integer guohuoFee;
    private Integer totalFee;

}
