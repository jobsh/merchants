package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 费用管理对象 biz_contract_fee
 * 
 * @author hanke
 * @date 2020-11-10
 */
@Data
public class  ContractFee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同费用表id */
    private Long id;

    private Integer receiverId;

    /** 费用编号 */
    @Excel(name = "费用编号")
    private String num;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String contractNum;

    /** 费用类型 */
    @Excel(name = "费用类型")
    private String type;

    /** 金额 */
    @Excel
    private String amount;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String payMethod;

    /** 付款人 */
    @Excel(name = "付款人")
    private String payer;

    /** 收款人 */
    @Excel(name = "收款人")
    private String reciever;

    /** 费用状态 */
    @Excel(name = "费用状态")
    private String checkStatus;

    /** 费用状态 */
    @Excel(name = "费用状态")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkDate;

    /** 收款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shoukuanDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
}
