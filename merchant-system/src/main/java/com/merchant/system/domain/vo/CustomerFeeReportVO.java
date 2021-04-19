package com.merchant.system.domain.vo;

import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @Classname CustomerFeeReportVO
 * @Description TODO
 * @Date 2020/12/2 11:43
 * @Created by hanke
 */
@Data
public class CustomerFeeReportVO extends BaseEntity {

    @Excel(name = "负责人姓名")
    private String userName;
    private String status;
    @Excel(name = "负责人电话")
    private String phonenumber;
    @Excel(name = "负责人部门名称")
    private String deptName;
    @Excel(name = "公司名称")
    private String companyName;
    @Excel(name = "职务")
    private String postName;
    @Excel(name = "履约保证金")
    private Integer lvyueFee;
    @Excel(name = "经营管理费")
    private Integer jingyingManagerFee;
    @Excel(name = "运营管理费")
    private Integer yunyingManagerFee;
    @Excel(name = "系统使用费")
    private Integer systemUseFee;
    @Excel(name = "系统维护费")
    private Integer systemMaintenanceFee;
    @Excel(name = "代办贷款费")
    private Integer daibanFee;
    @Excel(name = "代办过户费")
    private Integer guohuoFee;
    private Integer jiamengManagerFee;
    private Integer yejiBuchangjin;
    @Excel(name = "总费用")
    private Integer totalFee;

}
