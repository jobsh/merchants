package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我的客户对象 customer
 * 
 * @author hanke
 * @date 2020-10-21
 */
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户id */
    private Integer id;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String name;

    /** 客户电话 */
    @Excel(name = "客户电话")
    private String phone;

    /** 客户等级 */
    @Excel(name = "客户等级")
    private Integer level;

    /** 客户需求 */
    @Excel(name = "客户需求")
    private Integer customerNeeds;

    /** 客户公司 */
    @Excel(name = "客户公司")
    private String companyName;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 跟进状态 */
    @Excel(name = "跟进状态")
    private Integer genjinStatus;

    /** 客户来源 */
    @Excel(name = "客户来源")
    private String resource;

    /** 负责人id */
    @Excel(name = "负责人id")
    private Integer userId;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String username;

    /** 录入人 */
    @Excel(name = "录入人")
    private String luruName;

    /** 线索还是客户（0：线索；1：客户） */
    @Excel(name = "线索还是客户", readConverterExp = "0=：线索；1：客户")
    private Integer status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** 客户跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "客户跟进时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date genjinDate;

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
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public void setCustomerNeeds(Integer customerNeeds) 
    {
        this.customerNeeds = customerNeeds;
    }

    public Integer getCustomerNeeds() 
    {
        return customerNeeds;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setDistrict(String district) 
    {
        this.district = district;
    }

    public String getDistrict() 
    {
        return district;
    }
    public void setGenjinStatus(Integer genjinStatus) 
    {
        this.genjinStatus = genjinStatus;
    }

    public Integer getGenjinStatus() 
    {
        return genjinStatus;
    }
    public void setResource(String resource) 
    {
        this.resource = resource;
    }

    public String getResource() 
    {
        return resource;
    }
    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setLuruName(String luruName) 
    {
        this.luruName = luruName;
    }

    public String getLuruName() 
    {
        return luruName;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
    public void setGenjinDate(Date genjinDate) 
    {
        this.genjinDate = genjinDate;
    }

    public Date getGenjinDate() 
    {
        return genjinDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("level", getLevel())
            .append("customerNeeds", getCustomerNeeds())
            .append("companyName", getCompanyName())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("genjinStatus", getGenjinStatus())
            .append("resource", getResource())
            .append("userId", getUserId())
            .append("username", getUsername())
            .append("luruName", getLuruName())
            .append("status", getStatus())
            .append("inputDate", getInputDate())
            .append("updateDate", getUpdateDate())
            .append("genjinDate", getGenjinDate())
            .toString();
    }
}
