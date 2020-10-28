package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import com.merchant.common.enums.CustomerStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 我的客户对象 biz_customer
 * 
 * @author hanke
 * @date 2020-10-28
 */
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户表主键id */
    private Integer id;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String name;

    /** 客户电话 */
    @Excel(name = "客户电话")
    private String phone;

    /** 客户等级 */
    @Excel(name = "客户等级")
    private String level;

    /** 客户需求 */
    @Excel(name = "客户需求")
    private String customerNeeds;

    /** 公司和部门 */
    @Excel(name = "公司和部门")
    private String companyName;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 门店地址 */
    @Excel(name = "门店地址")
    private String dianmianAddress;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 客户来源 */
    @Excel(name = "客户来源")
    private String resource;

    /** 负责人id */
    private Integer userId;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String username;

    /** 录入人 */
    @Excel(name = "录入人")
    private String luruName;

    public String getUsername() {
        return username;
    }

    public void setLuruName(String luruName) {
        this.luruName = luruName;
    }

    /** 中介经验 */
    @Excel(name = "中介经验")
    private String experience;

    /**  */
    private String status;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setCustomerNeeds(String customerNeeds) 
    {
        this.customerNeeds = customerNeeds;
    }

    public String getCustomerNeeds() 
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
    public void setDianmianAddress(String dianmianAddress) 
    {
        this.dianmianAddress = dianmianAddress;
    }

    public String getDianmianAddress() 
    {
        return dianmianAddress;
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

    public String getLuruName() 
    {
        return luruName;
    }
    public void setExperience(String experience) 
    {
        this.experience = experience;
    }

    public String getExperience() 
    {
        return experience;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus() 
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("level", getLevel())
            .append("customerNeeds", getCustomerNeeds())
            .append("companyName", getCompanyName())
            .append("province", getProvince())
            .append("dianmianAddress", getDianmianAddress())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("resource", getResource())
            .append("userId", getUserId())
            .append("luruName", getLuruName())
            .append("experience", getExperience())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("inputDate", getInputDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
