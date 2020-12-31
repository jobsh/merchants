package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import com.merchant.common.core.domain.entity.SysCompany;
import com.merchant.system.domain.Contract;
import com.merchant.system.domain.ContractFee;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 费用管理对象 ContractFeeBO
 * 
 * @author hanke
 * @date 2020-11-10
 */
@Data
public class ContractFeeBO extends ContractFee {

    /** 部门id */
    private Integer deptId;
    /** 部门名称 */
    private String deptName;
    /** 公司名称 */
    private String companyName;
    /** 签约产品 */
    private String produce;
    /** 合同负责人id */
    private Integer managerId;
    /** 收款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收款时间开始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shoukuanDateStart;
    /** 收款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收款时间截止", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shoukuanDateEnd;

}
