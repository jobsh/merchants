package com.merchant.system.domain.vo;

import lombok.Data;

/**
 * @Classname CustomerFeeReportVO
 * @Description TODO
 * @Date 2020/12/2 11:43
 * @Created by hanke
 */
@Data
public class CustomerFeeReportVO {

    private String userName;
    private String phonenumber;
    private String deptName;
    private String companyName;
    private String postName;
    private Integer lvyueFee;
    private Integer jingyingManagerFee;
    private Integer yunyingManagerFee;
    private Integer systemUseFee;
    private Integer systemMaintenanceFee;
    private Integer daibanFee;
    private Integer guohuoFee;
    private Integer totalFee;

}
