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
public class JingyingManagerFeeReportVO extends BaseEntity {

    private String userName; // 负责人名称
    private String postName; // 职务
    private String contractNum; // 合同编号
    private String dianmianName;
    private String deptName; // 公司部门
    private String customerName; // 公司部门
    private String customerPhone; // 客户手机号
    private String jingyingManagerFeeYingshou;
    private Integer jingyingManagerFeeYishou;

}
