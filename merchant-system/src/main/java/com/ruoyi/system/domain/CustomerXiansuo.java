package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户线索对象 customer_xiansuo
 * 
 * @author ruoyi
 * @date 2020-10-19
 */
public class CustomerXiansuo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String name;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 城市 */
    @Excel(name = "城市")
    private Integer companyId;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 线索来源 */
    @Excel(name = "线索来源")
    private String resource;

    /** 负责人 */
    @Excel(name = "负责人")
    private Long sysUserId;

    /** 是否线索变为客户(0：否，1：是) */
    private Integer isCustomer;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setCompanyId(Integer companyId) 
    {
        this.companyId = companyId;
    }

    public Integer getCompanyId() 
    {
        return companyId;
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
    public void setResource(String resource) 
    {
        this.resource = resource;
    }

    public String getResource() 
    {
        return resource;
    }
    public void setSysUserId(Long sysUserId) 
    {
        this.sysUserId = sysUserId;
    }

    public Long getSysUserId() 
    {
        return sysUserId;
    }
    public void setIsCustomer(Integer isCustomer) 
    {
        this.isCustomer = isCustomer;
    }

    public Integer getIsCustomer() 
    {
        return isCustomer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("companyId", getCompanyId())
            .append("province", getProvince())
            .append("city", getCity())
            .append("resource", getResource())
            .append("sysUserId", getSysUserId())
            .append("isCustomer", getIsCustomer())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
