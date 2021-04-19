package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.Serializers;
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
public class DianmianBO extends BaseEntity {

    /** 店面表主键id */
    @ApiModelProperty(value = "店面表主键id", name = "id")
    private Integer id;

    /** 所属部门id */
    @ApiModelProperty(value = "所属部门id", name = "deptId")
    private Integer deptId;

    @ApiModelProperty(value = "所属部门名称", name = "deptName")
    private String deptName;

    /** 所属公司name */
    private String companyName;

    /** 店东id */
    @ApiModelProperty(value = "店东id", name = "diandongId")
    private Integer diandongId;

    /** 管理人id */
    @ApiModelProperty(value = "负责人id", name = "userId")
    private Long userId;

    /** 所属公司id */
    @Excel(name = "所属公司id")
    @ApiModelProperty(value = "所属公司id", name = "companyId")
    private Integer companyId;

    /** 店东姓名 */
    @ApiModelProperty(value = "店东姓名", name = "diandongName")
    private String diandongName;

    /** 店东电话 */
    @ApiModelProperty(value = "店东姓名", name = "diandongPhone")
    private String diandongPhone;

    /** 门店名称 */
    @ApiModelProperty(value = "门店名称", name = "name")
    private String name;

    /** 门店状态 */
    @ApiModelProperty(value = "门店状态", name = "status")
    private String status;

    /** 所属省 */
    @ApiModelProperty(value = "所属省", name = "province")
    private String province;

    /** 所属市 */
    @ApiModelProperty(value = "所属市", name = "city")
    private String city;

    /** 所属区 */
    @ApiModelProperty(value = "所属区", name = "district")
    private String district;

    /** 经度 */
    @ApiModelProperty(value = "经度", name = "longitude")
    private String longitude;

    /** 纬度 */
    @ApiModelProperty(value = "纬度", name = "latitude")
    private String latitude;

    /** 店面详细地址 */
    @ApiModelProperty(value = "店面详细地址", name = "address")
    private String address;

    /** 验收结果 */
    @ApiModelProperty(value = "验收结果", name = "checkResult")
    private String checkResult;

    /** 合同编号 */
    @ApiModelProperty(value = "合同编号", name = "contractNum")
    private String contractNum;

    /** 店面类型（0：单店加盟，1：区域加盟） */
    @ApiModelProperty(value = "店面类型（0：单店加盟，1：区域加盟）", name = "type")
    private String type;

    /** 授权区域 */
    @ApiModelProperty(value = "授权区域", name = "area")
    private String area;

    /** 负责人 */
    @ApiModelProperty(value = "负责人名称", name = "username")
    private String username;

    /** 授权区域 */
    @ApiModelProperty(value = "关键字", name = "keywords")
    private String keywords;

    /** 验收信息 */
    @ApiModelProperty(value = "验收信息", name = "checkInfo")
    private String checkInfo;

    /** 开店时间*/
    @ApiModelProperty(value = "开店时间", name = "setDate")
    private String setDate;

    /** 开店时间*/
    @ApiModelProperty(value = "营业时间", name = "openDate")
    private String openDate;

    /** 开店时间*/
    @ApiModelProperty(value = "休业时间", name = "restDate")
    private String restDate;

    /** 开店时间*/
    @ApiModelProperty(value = "闭店时间", name = "closeDate")
    private String closeDate;


    @ApiModelProperty(value = "最早录入时间", name = "inputDateStart")
    private String inputDateStart;

    @ApiModelProperty(value = "最晚录入时间", name = "inputDateEnd")
    private String inputDateEnd;

    /** 开店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最早开店时间", name = "openDateStart")
    private String openDateStart;

    /** 开店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最晚录入时间", name = "openDateEnd")
    private String openDateEnd;

    /** 开店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最早开店时间", name = "openDateStart")
    private String setDateStart;

    /** 开店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最晚录入时间", name = "openDateEnd")
    private String setDateEnd;

    /** 闭店/停业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最早闭店停业时间", name = "closeDateStart")
    private String closeDateStart;

    /** 闭店/停业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最晚停业时间", name = "closeDateEnd")
    private String closeDateEnd;

}
