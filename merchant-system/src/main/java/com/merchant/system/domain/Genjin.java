package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户跟进对象 biz_genjin
 * 
 * @author hanke
 * @date 2020-10-29
 */
public class Genjin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 跟进表主键 */
    private Integer id;

    /** 客户id */
    @Excel(name = "客户id")
    private Integer customerId;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String customerName;

    /** 跟进人id */
    @Excel(name = "跟进人id")
    private Long userId;

    /** 写跟进的内容 */
    @Excel(name = "写跟进的内容")
    private String content;

    /** 跟进方式 */
    @Excel(name = "跟进方式")
    private String method;

    /** 跟进状态(0：跟进；1：到访；2：意向；3：报价；4：成交；5：解约；6：暂时搁置） */
    @Excel(name = "跟进状态(0：跟进；1：到访；2：意向；3：报价；4：成交；5：解约；6：暂时搁置）")
    private String status;

    /** 写跟进插入的图片 */
    private String image;

    /** 跟进时间,即录入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "跟进时间,即录入时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date genjinDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date updateDate;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCustomerId(Integer customerId) 
    {
        this.customerId = customerId;
    }

    public Integer getCustomerId() 
    {
        return customerId;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setuserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getuserId()
    {
        return userId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setMethod(String method) 
    {
        this.method = method;
    }

    public String getMethod() 
    {
        return method;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setGenjinDate(Date genjinDate) 
    {
        this.genjinDate = genjinDate;
    }

    public Date getGenjinDate() 
    {
        return genjinDate;
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
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("userId", getuserId())
            .append("content", getContent())
            .append("method", getMethod())
            .append("status", getStatus())
            .append("image", getImage())
            .append("genjinDate", getGenjinDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
