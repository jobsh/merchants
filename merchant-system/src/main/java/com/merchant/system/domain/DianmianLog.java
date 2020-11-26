package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 店面日志对象 biz_dianmian_log
 * 
 * @author hanke
 * @date 2020-11-26
 */
public class DianmianLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 店面日志id */
    private Integer id;

    /** 店面id */
    @Excel(name = "店面id")
    private Integer dianmianId;

    /** 具体操作（开店闭店） */
    @Excel(name = "具体操作", readConverterExp = "开=店闭店")
    private String oper;

    /** 描述（闭店原因） */
    @Excel(name = "描述", readConverterExp = "闭=店原因")
    private String discription;

    /** 门店状态 */
    @Excel(name = "门店状态")
    private String status;

    /** 开店时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开店时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date openDate;

    /** 闭店时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "闭店时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closeDate;

    /** 插入时间 */
    private Date inputDate;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setDianmianId(Integer dianmianId) 
    {
        this.dianmianId = dianmianId;
    }

    public Integer getDianmianId() 
    {
        return dianmianId;
    }
    public void setOper(String oper) 
    {
        this.oper = oper;
    }

    public String getOper() 
    {
        return oper;
    }
    public void setDiscription(String discription) 
    {
        this.discription = discription;
    }

    public String getDiscription() 
    {
        return discription;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setOpenDate(Date openDate) 
    {
        this.openDate = openDate;
    }

    public Date getOpenDate() 
    {
        return openDate;
    }
    public void setCloseDate(Date closeDate) 
    {
        this.closeDate = closeDate;
    }

    public Date getCloseDate() 
    {
        return closeDate;
    }
    public void setInputDate(Date inputDate) 
    {
        this.inputDate = inputDate;
    }

    public Date getInputDate() 
    {
        return inputDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dianmianId", getDianmianId())
            .append("oper", getOper())
            .append("discription", getDiscription())
            .append("status", getStatus())
            .append("openDate", getOpenDate())
            .append("closeDate", getCloseDate())
            .append("inputDate", getInputDate())
            .append("remark", getRemark())
            .toString();
    }
}
