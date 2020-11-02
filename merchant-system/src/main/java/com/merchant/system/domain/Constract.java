package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 合同管理对象 biz_constract
 * 
 * @author hanke
 * @date 2020-11-02
 */
public class Constract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同表主键id */
    private Integer id;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String num;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String customerName;

    /** 客户id */
    private Long customerId;

    /** 客户手机号 */
    @Excel(name = "客户手机号")
    private String customerPhone;

    /** 合同类型 */
    @Excel(name = "合同类型")
    private String type;

    /** 签约产品 */
    @Excel(name = "签约产品")
    private String produce;

    /** 店面或区域名称 */
    @Excel(name = "店面或区域名称")
    private String dianmianName;

    /** 店面数量 */
    @Excel(name = "店面数量")
    private Integer dianmianNum;

    /** 保证金 */
    @Excel(name = "保证金")
    private Long guarantee;

    /** 费用 */
    @Excel(name = "费用")
    private String fee;

    /** 操作 */
    @Excel(name = "操作")
    private String operation;

    /** 负责人 */
    @Excel(name = "负责人")
    private String manager;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signDate;

    /** 签约人员 */
    @Excel(name = "签约人员")
    private String signUser;

    /** 合同开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /** 合同结束日期 */
    @Excel(name = "合同结束日期")
    private String endDate;

    /** 生效失效状态 */
    @Excel(name = "生效失效状态")
    private String status;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
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
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setCustomerPhone(String customerPhone) 
    {
        this.customerPhone = customerPhone;
    }

    public String getCustomerPhone() 
    {
        return customerPhone;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setProduce(String produce) 
    {
        this.produce = produce;
    }

    public String getProduce() 
    {
        return produce;
    }
    public void setDianmianName(String dianmianName) 
    {
        this.dianmianName = dianmianName;
    }

    public String getDianmianName() 
    {
        return dianmianName;
    }
    public void setDianmianNum(Integer dianmianNum) 
    {
        this.dianmianNum = dianmianNum;
    }

    public Integer getDianmianNum() 
    {
        return dianmianNum;
    }
    public void setGuarantee(Long guarantee) 
    {
        this.guarantee = guarantee;
    }

    public Long getGuarantee() 
    {
        return guarantee;
    }
    public void setFee(String fee) 
    {
        this.fee = fee;
    }

    public String getFee() 
    {
        return fee;
    }
    public void setOperation(String operation) 
    {
        this.operation = operation;
    }

    public String getOperation() 
    {
        return operation;
    }
    public void setManager(String manager) 
    {
        this.manager = manager;
    }

    public String getManager() 
    {
        return manager;
    }
    public void setSignDate(Date signDate) 
    {
        this.signDate = signDate;
    }

    public Date getSignDate() 
    {
        return signDate;
    }
    public void setSignUser(String signUser) 
    {
        this.signUser = signUser;
    }

    public String getSignUser() 
    {
        return signUser;
    }
    public void setBeginDate(Date beginDate) 
    {
        this.beginDate = beginDate;
    }

    public Date getBeginDate() 
    {
        return beginDate;
    }
    public void setEndDate(String endDate) 
    {
        this.endDate = endDate;
    }

    public String getEndDate() 
    {
        return endDate;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("num", getNum())
            .append("customerName", getCustomerName())
            .append("customerId", getCustomerId())
            .append("customerPhone", getCustomerPhone())
            .append("type", getType())
            .append("produce", getProduce())
            .append("dianmianName", getDianmianName())
            .append("dianmianNum", getDianmianNum())
            .append("guarantee", getGuarantee())
            .append("fee", getFee())
            .append("operation", getOperation())
            .append("manager", getManager())
            .append("signDate", getSignDate())
            .append("signUser", getSignUser())
            .append("beginDate", getBeginDate())
            .append("endDate", getEndDate())
            .append("status", getStatus())
            .append("remark", getRemark())
            .toString();
    }
}
