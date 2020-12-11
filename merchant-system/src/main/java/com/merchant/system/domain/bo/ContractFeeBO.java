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

    /** 部门名称 */
    private String deptName;
    /** 公司名称 */
    private String companyName;
    /** 签约产品 */
    private String produce;

}
