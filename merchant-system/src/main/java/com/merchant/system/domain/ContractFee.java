package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 费用管理对象 biz_contract_fee
 * 
 * @author hanke
 * @date 2020-11-10
 */
public class ContractFee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同费用表id */
    private Long id;

    /** 费用编号 */
    @Excel(name = "费用编号")
    private String num;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String constractNum;

    /** 费用类型 */
    @Excel(name = "费用类型")
    private String type;

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

    /** 收款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shoukuanDate;

    /** 更新时间 */
    private Date updateDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNum(String num) 
    {
        this.num = num;
    }

    public String getNum() 
    {
        return num;
    }
    public void setConstractNum(String constractNum) 
    {
        this.constractNum = constractNum;
    }

    public String getConstractNum() 
    {
        return constractNum;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setPayMethod(String payMethod) 
    {
        this.payMethod = payMethod;
    }

    public String getPayMethod() 
    {
        return payMethod;
    }
    public void setPayer(String payer) 
    {
        this.payer = payer;
    }

    public String getPayer() 
    {
        return payer;
    }
    public void setReciever(String reciever) 
    {
        this.reciever = reciever;
    }

    public String getReciever() 
    {
        return reciever;
    }
    public void setCheckStatus(String checkStatus) 
    {
        this.checkStatus = checkStatus;
    }

    public String getCheckStatus() 
    {
        return checkStatus;
    }
    public void setShoukuanDate(Date shoukuanDate) 
    {
        this.shoukuanDate = shoukuanDate;
    }

    public Date getShoukuanDate() 
    {
        return shoukuanDate;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("constractNum", getConstractNum())
            .append("type", getType())
            .append("payMethod", getPayMethod())
            .append("payer", getPayer())
            .append("reciever", getReciever())
            .append("checkStatus", getCheckStatus())
            .append("shoukuanDate", getShoukuanDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
