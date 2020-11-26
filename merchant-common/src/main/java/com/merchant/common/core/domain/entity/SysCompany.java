package com.merchant.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 公司对象 sys_company
 * 
 * @author hanke
 * @date 2020-11-25
 */
public class SysCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 公司全称 */
    @Excel(name = "公司全称")
    private String name;

    /** 公司简称 */
    @Excel(name = "公司简称")
    private String simpleName;

    /** 账号 */
    private String sourceName;

    /** 权健码 */
    private String sign;

    /** 公司类型 */
    private String type;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactPerson;

    /** 地址 */
    private String address;

    /** 公司营业执照图片 */
    private String image;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 公司简介 */
    private String introduce;

    /** 录入时间 */
    private Date inputDate;

    /** 城市ID */
    private Integer cityId;

    /** 公司状态 */
    private String status;

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
    public void setSimpleName(String simpleName) 
    {
        this.simpleName = simpleName;
    }

    public String getSimpleName() 
    {
        return simpleName;
    }
    public void setSourceName(String sourceName) 
    {
        this.sourceName = sourceName;
    }

    public String getSourceName() 
    {
        return sourceName;
    }
    public void setSign(String sign) 
    {
        this.sign = sign;
    }

    public String getSign() 
    {
        return sign;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setIntroduce(String introduce) 
    {
        this.introduce = introduce;
    }

    public String getIntroduce() 
    {
        return introduce;
    }
    public void setInputDate(Date inputDate) 
    {
        this.inputDate = inputDate;
    }

    public Date getInputDate() 
    {
        return inputDate;
    }
    public void setCityId(Integer cityId) 
    {
        this.cityId = cityId;
    }

    public Integer getCityId() 
    {
        return cityId;
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
            .append("name", getName())
            .append("simpleName", getSimpleName())
            .append("sourceName", getSourceName())
            .append("sign", getSign())
            .append("type", getType())
            .append("contactPerson", getContactPerson())
            .append("address", getAddress())
            .append("image", getImage())
            .append("phone", getPhone())
            .append("introduce", getIntroduce())
            .append("inputDate", getInputDate())
            .append("cityId", getCityId())
            .append("status", getStatus())
            .toString();
    }
}
