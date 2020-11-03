package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 店面管理对象 biz_dianmian
 * 
 * @author hanke
 * @date 2020-11-02
 */
@Data
public class DianmianBO {
    /** 店东姓名 */
    @Excel(name = "店东姓名")
    private String diandongName;

    /** 店东电话 */
    @Excel(name = "店东电话")
    private String diandongPhone;

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

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String contractnum;

    /** 店面类型（0：单店加盟，1：区域加盟） */
    @Excel(name = "店面类型", readConverterExp = "0=：单店加盟，1：区域加盟")
    private String type;

    /** 授权区域 */
    @Excel(name = "授权区域")
    private String area;

    /** 验收信息 */
    @Excel(name = "验收信息")
    private String checkInfo;

    @ApiModelProperty(value = "最早录入时间", name = "inputDateStart")
    private String inputDateStart;

    @ApiModelProperty(value = "最近录入时间", name = "inputDateEnd")
    private String inputDateEnd;

    /** 开店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最早开店日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String openDateStart;

    /** 开店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最晚开店日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String openDateEnd;

    /** 闭店/停业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最早闭店/停业时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String closeDateStart;

    /** 闭店/停业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最晚闭店/停业时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String closeDateEnd;

}
