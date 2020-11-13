package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 店面管理对象 biz_dianmian
 * 
 * @author hanke
 * @date 2020-11-02
 */
@Data
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
    private String province;

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
    private String contractNum;

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
}
