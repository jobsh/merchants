package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 店面管理对象 biz_dianmian
 * 
 * @author hanke
 * @date 2020-11-02
 */
public class Dianmian extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 店面表主键id */
    private Integer id;

    /** 店东id */
    @Excel(name = "店东id")
    private Integer diandongId;

    /** 店东姓名 */
    @Excel(name = "店东姓名")
    private String diandongName;

    /** 店东电话 */
    @Excel(name = "店东电话")
    private String diandongPhone;

    /** 管理人id */
    @Excel(name = "管理人id")
    private Long sysUserId;

    /** 所属公司id */
    @Excel(name = "所属公司id")
    private Integer companyId;

    /** 门店名称 */
    @Excel(name = "门店名称")
    private String name;

    /** 门店状态 */
    @Excel(name = "门店状态")
    private String status;

    /** 所属省 */
    @Excel(name = "所属省")
    private String provice;

    /** 所属市 */
    @Excel(name = "所属市")
    private String city;

    /** 所属区 */
    @Excel(name = "所属区")
    private String district;

    /** 经度 */
    private String longitude;

    /** 纬度 */
    private String latitude;

    /** 店面详细地址 */
    @Excel(name = "店面详细地址")
    private String address;

    /** 验收结果 */
    @Excel(name = "验收结果")
    private String checkResult;

    /** 开店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开店日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date openDate;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String contractnum;

    /** 店面类型（0：单店加盟，1：区域加盟） */
    @Excel(name = "店面类型", readConverterExp = "0=：单店加盟，1：区域加盟")
    private String type;

    /** 授权区域 */
    @Excel(name = "授权区域")
    private String area;

    /** 闭店/停业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "闭店/停业时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closeDate;

    /** 暂停营业时长 */
    private Integer closeDays;

    /** 闭店原因 */
    @Excel(name = "闭店原因")
    private String closeReason;

    /** 验收信息 */
    @Excel(name = "验收信息")
    private String checkInfo;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setDiandongId(Integer diandongId) 
    {
        this.diandongId = diandongId;
    }

    public Integer getDiandongId() 
    {
        return diandongId;
    }
    public void setDiandongName(String diandongName) 
    {
        this.diandongName = diandongName;
    }

    public String getDiandongName() 
    {
        return diandongName;
    }
    public void setDiandongPhone(String diandongPhone) 
    {
        this.diandongPhone = diandongPhone;
    }

    public String getDiandongPhone() 
    {
        return diandongPhone;
    }
    public void setSysUserId(Long sysUserId) 
    {
        this.sysUserId = sysUserId;
    }

    public Long getSysUserId() 
    {
        return sysUserId;
    }
    public void setCompanyId(Integer companyId) 
    {
        this.companyId = companyId;
    }

    public Integer getCompanyId() 
    {
        return companyId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setProvice(String provice) 
    {
        this.provice = provice;
    }

    public String getProvice() 
    {
        return provice;
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
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setCheckResult(String checkResult) 
    {
        this.checkResult = checkResult;
    }

    public String getCheckResult() 
    {
        return checkResult;
    }
    public void setOpenDate(Date openDate) 
    {
        this.openDate = openDate;
    }

    public Date getOpenDate() 
    {
        return openDate;
    }
    public void setContractnum(String contractnum) 
    {
        this.contractnum = contractnum;
    }

    public String getContractnum() 
    {
        return contractnum;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setCloseDate(Date closeDate) 
    {
        this.closeDate = closeDate;
    }

    public Date getCloseDate() 
    {
        return closeDate;
    }
    public void setCloseDays(Integer closeDays) 
    {
        this.closeDays = closeDays;
    }

    public Integer getCloseDays() 
    {
        return closeDays;
    }
    public void setCloseReason(String closeReason) 
    {
        this.closeReason = closeReason;
    }

    public String getCloseReason() 
    {
        return closeReason;
    }
    public void setCheckInfo(String checkInfo) 
    {
        this.checkInfo = checkInfo;
    }

    public String getCheckInfo() 
    {
        return checkInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("diandongId", getDiandongId())
            .append("diandongName", getDiandongName())
            .append("diandongPhone", getDiandongPhone())
            .append("sysUserId", getSysUserId())
            .append("companyId", getCompanyId())
            .append("name", getName())
            .append("status", getStatus())
            .append("provice", getProvice())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("address", getAddress())
            .append("checkResult", getCheckResult())
            .append("openDate", getOpenDate())
            .append("contractnum", getContractnum())
            .append("type", getType())
            .append("area", getArea())
            .append("closeDate", getCloseDate())
            .append("closeDays", getCloseDays())
            .append("closeReason", getCloseReason())
            .append("checkInfo", getCheckInfo())
            .append("remark", getRemark())
            .toString();
    }
}
