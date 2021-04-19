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
public class JingyingManagerFeeReportVO extends BaseEntity {
    @Excel(name = "公司")
    private String companyName; // 公司
    @Excel(name = "部门")
    private String deptName; // 公司部门
    @Excel(name = "负责人名称")
    private String userName; // 负责人名称
    private String status;
    @Excel(name = "负责人职务")
    private String postName; // 职务
    private String contractNum; // 合同编号
    @Excel(name = "合同编号")
    private String contractCode; // 合同编号 手动输入
    @Excel(name = "店面/区域名称")
    private String dianmianName; // 店面名称
    @Excel(name = "客户名称")
    private String customerName; // 公司部门
    @Excel(name = "客户电话")
    private String customerPhone; // 客户手机号
    @Excel(name = "应收经营管理费")
    private String jingyingManagerFeeYingshou;
    @Excel(name = "已收经营管理费")
    private Integer jingyingManagerFeeYishou;
}
