package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 费用管理对象 biz_contract_fee
 * 
 * @author hanke
 * @date 2020-11-10
 */
@Data
public class AddContractFeeBO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同费用表id */
    private Integer id;

    @NotNull(message = "没有上传收款人id")
    private Integer receiverId;

    /** 费用编号 */
    @Excel(name = "费用编号")
    private String num;

    /** 合同编号 */
    @Excel(name = "合同编号")
    @NotNull(message = "没有上传关联的合同编号")
    private String contractNum;

    /** 费用类型 */
    @Excel(name = "费用类型")
    @NotNull(message = "没有上传费用类型")
    private String type;

    /** 金额 */
    @Excel
    @NotNull(message = "没有上传费用金额")
    private String amount;

    /** 付款方式 */
    @Excel(name = "付款方式")
    @NotNull(message = "没有上传付款方式")
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
    @NotNull(message = "没有上传收款日期")
    private Date shoukuanDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
}
