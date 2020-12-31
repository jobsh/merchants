package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import com.merchant.system.domain.Dianmian;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 店面管理对象 biz_dianmian
 * 
 * @author hanke
 * @date 2020-11-02
 */
@Data
public class AddDianmianBO extends BaseEntity {

    /** 店面表主键id */
    @ApiModelProperty(value = "店面表主键id", name = "id")
    private Integer id;

    /** 所属部门id */
    @NotNull(message = "没有上传部门id")
    private Integer deptId;

    /** 所属公司name */
    private String companyName;

    /** 店东id */
    @ApiModelProperty(value = "店东id", name = "diandongId")
    @NotNull(message = "没有上传店东id")
    private Integer diandongId;

    /** 管理人id */
    @ApiModelProperty(value = "负责人id", name = "userId")
    @NotNull(message = "没有上传负责人id")
    private Long userId;

    /** 所属公司id */
    @Excel(name = "所属公司id")
    @ApiModelProperty(value = "所属公司id", name = "companyId")
    private Integer companyId;

    /** 店东姓名 */
    @ApiModelProperty(value = "店东姓名", name = "diandongName")
    @NotNull(message = "没有上传店东姓名")
    private String diandongName;

    /** 店东电话 */
    @ApiModelProperty(value = "店东手机号", name = "diandongPhone")
    @Pattern(regexp = "^1[3456789][0-9]{9}(,1[3456789][0-9]{9})*$", message = "手机号格式不正确")
    @NotNull(message = "没有上传店东手机号")
    private String diandongPhone;

    /** 门店名称 */
    @ApiModelProperty(value = "门店名称", name = "name")
    @NotNull(message = "没有上传店面名称")
    private String name;

    /** 门店状态 */
    @ApiModelProperty(value = "门店状态", name = "status")
    private String status;

    /** 所属省 */
    @ApiModelProperty(value = "所属省", name = "province")
    @NotNull(message = "没有上传所属省")
    private String province;

    /** 所属市 */
    @ApiModelProperty(value = "所属市", name = "city")
    @NotNull(message = "没有上传所属市")
    private String city;

    /** 所属区 */
    @ApiModelProperty(value = "所属区", name = "district")
    @NotNull(message = "没有上传所属区")
    private String district;

    /** 经度 */
    @ApiModelProperty(value = "经度", name = "longitude")
    @NotNull(message = "没有上传经度坐标")
    private String longitude;

    /** 纬度 */
    @ApiModelProperty(value = "纬度", name = "latitude")
    @NotNull(message = "没有上传纬度坐标")
    private String latitude;

    /** 店面详细地址 */
    @ApiModelProperty(value = "店面详细地址", name = "address")
    @NotNull(message = "没有上传店面详细地址")
    private String address;

    /** 验收结果 */
    @ApiModelProperty(value = "验收结果", name = "checkResult")
    private String checkResult;

    /** 验收结果 */
    @ApiModelProperty(value = "验收信息", name = "checkInfo")
    private String checkInfo;

    /** 合同编号 */
    @ApiModelProperty(value = "合同编号", name = "contractNum")
    @NotNull(message = "没有上传店面关联的合同编号")
    private String contractNum;

    /** 店面类型（0：单店加盟，1：区域加盟） */
    @ApiModelProperty(value = "店面类型（0：单店加盟，1：区域加盟）", name = "type")
    @NotNull(message = "没有上传店面type")
    private String type;

    /** 授权区域 */
    @ApiModelProperty(value = "授权区域", name = "area")
    private String area;

    /** 负责人 */
    @ApiModelProperty(value = "负责人名称", name = "username")
    private String username;

    /** 开店时间*/
    @ApiModelProperty(value = "开店时间", name = "openDate")
    private String setDate;

    /** 开店时间*/
    @ApiModelProperty(value = "开店时间", name = "openDate")
    private String openDate;

    /** 开店时间*/
    @ApiModelProperty(value = "开店时间", name = "openDate")
    private String restDate;

    /** 闭店时间*/
    @ApiModelProperty(value = "开店时间", name = "openDate")
    private String closeDate;

    /** 闭店时间*/
    @ApiModelProperty(value = "闭店原因", name = "closeReason")
    private String closeReason;

    /** 闭店时间*/
    @ApiModelProperty(value = "闭店天数", name = "closeDays")
    private Integer closeDays;

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

    /** 闭店/停业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最早闭店停业时间", name = "closeDateStart")
    private String closeDateStart;

    /** 闭店/停业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "最晚停业时间", name = "closeDateEnd")
    private String closeDateEnd;

}
