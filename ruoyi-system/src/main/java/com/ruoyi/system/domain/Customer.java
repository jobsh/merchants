package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我的客户对象 customer
 * 
 * @author hanke
 * @date 2020-10-19
 */
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户id */
    private Integer id;

    /** $column.columnComment */
    private Integer xiansuoId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String name;

    /** 客户电话 */
    @Excel(name = "客户电话")
    private String phone;

    /** 客户等级 */
    @Excel(name = "客户等级")
    private Integer level;

    /** 公司 */
    @Excel(name = "公司")
    private Integer companyId;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 跟进状态 */
    @Excel(name = "跟进状态")
    private Integer genjinStatus;

    /** 客户来源 */
    @Excel(name = "客户来源")
    private String resource;

    /** 负责人 */
    @Excel(name = "负责人")
    private Integer sysUserId;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setXiansuoId(Integer xiansuoId) 
    {
        this.xiansuoId = xiansuoId;
    }

    public Integer getXiansuoId() 
    {
        return xiansuoId;
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
    public void setSysUserId(Integer sysUserId) 
    {
        this.sysUserId = sysUserId;
    }

    public Integer getSysUserId() 
    {
        return sysUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("xiansuoId", getXiansuoId())
            .append("name", getName())
            .append("phone", getPhone())
            .append("level", getLevel())
            .append("companyId", getCompanyId())
            .append("province", getProvince())
            .append("city", getCity())
            .append("genjinStatus", getGenjinStatus())
            .append("resource", getResource())
            .append("sysUserId", getSysUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
