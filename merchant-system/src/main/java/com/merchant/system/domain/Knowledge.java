package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 知识库对象 biz_knowledge
 * 
 * @author hanke
 * @date 2020-12-18
 */
public class Knowledge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 描述 */
    @Excel(name = "描述")
    private String discription;

    /** 知识库地址 */
    @Excel(name = "知识库地址")
    private String url;

    /** 插入时间 */
    private Date inputDate;

    /** 更新时间 */
    private Date updateDate;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setDiscription(String discription) 
    {
        this.discription = discription;
    }

    public String getDiscription() 
    {
        return discription;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setInputDate(Date inputDate) 
    {
        this.inputDate = inputDate;
    }

    public Date getInputDate() 
    {
        return inputDate;
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
            .append("name", getName())
            .append("type", getType())
            .append("discription", getDiscription())
            .append("url", getUrl())
            .append("remark", getRemark())
            .append("inputDate", getInputDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
