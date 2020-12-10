package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 合同对象 biz_contract
 * 
 * @author hanke
 * @date 2020-11-03
 */
@Data
public class AddContractBO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同表主键id */
    @ApiModelProperty(value = "合同id", name = "id")
    private Integer id;

    /** 合同表主键ids */
    @ApiModelProperty(value = "合同ids", name = "ids")
    private Integer[] ids;

    /** 合同表主键id */
    @ApiModelProperty(value = "部门id", name = "deptId")
    @NotNull(message = "部门id不能为空")
    private Integer deptId;

    /** 上级合同id */
    private Integer pid;

    @ApiModelProperty(value = "关联合同编号", name = "rootNum")
    private String rootNum;

    /** 合同编号 */
    @ApiModelProperty(value = "合同编号", name = "num")
    @NotNull(message = "合同编号不能为空")
    private String num;

    /** 客户姓名 */
    @ApiModelProperty(value = "客户姓名", name = "customerName")
    @NotNull(message = "客户名不能为空")
    private String customerName;

    /** 客户id */
    @ApiModelProperty(value = "客户id", name = "customerId")
    @NotNull(message = "客户id不能为空")
    private Integer customerId;

    @ApiModelProperty(value = "客户编号", name = "customerNum")
    /** 客户编号 */
    @NotNull(message = "客户编号不能为空")
    private String customerNum;

    /** 客户手机号（可以有多个手机号，逗号分隔） */
    @ApiModelProperty(value = "客户手机号", name = "customerPhone")
    @NotNull(message = "客户手机号不能为空")
    private String customerPhone;

    /** 合同类型(0：新签:1：续签) */
    @Excel(name = "合同类型(0：新签:1：续签)")
    @ApiModelProperty(value = "合同类型(0：新签:1：续签)", name = "type")
    private String type;

    /** 签约产品 */
    @ApiModelProperty(value = "签约产品", name = "produce")
    @NotNull(message = "产品类型不能为空")
    private String produce;

    /** 店面或区域名称 */
    @ApiModelProperty(value = "店面或区域名称", name = "dianmianName")
    @NotNull(message = "店面或区域名称不能为空")
    private String dianmianName;

    /** 店面数量 */
    @Excel(name = "店面数量")
    @ApiModelProperty(value = "店面数量，单店加盟默认为1", name = "dianmianNum")
    private Integer dianmianNum;

    /** 店面所在省份 */
    @NotNull(message = "店面区域所在省份不能为空")
    private String dianmianProvince;

    /** 店面所在城市 */
    @NotNull(message = "店面区域所在城市不能为空")
    private String dianmianCity;

    /** 店面所在区 */
    private String dianmianDistrict;

    /** 店面详细地址 */
    private String dianmianAddress;

    /** 店面经度 */
    private String dianmianLongitude;

    /** 店面纬度 */
    private String dianmianLatitude;

    /** 保证金 */
    @ApiModelProperty(value = "保证金,单位分显示时需要转化为元", name = "guarantee")
    @NotNull(message = "店面保证金不能为空")
    private Integer guarantee;

    /** 各种费用 */
    @ApiModelProperty(value = "各种费用", name = "fee")
    private String fee;

    /** 操作 */
    @ApiModelProperty(value = "对合同的操作", name = "operation")
    private String operation;

    /** 生效失效状态 */
    @Excel(name = "生效失效状态")
    @ApiModelProperty(value = "生效失效状态", name = "status")
    private String status;

    /** 审核状态（0:未审核  1:已审核） */
    private String checkStatus;

    private Integer managerId;

    /** 负责人 */
    @Excel(name = "负责人")
    @ApiModelProperty(value = "负责人姓名", name = "manager")
    private String manager;

    private Integer signUserId;

    /** 签约人员 */
    @Excel(name = "签约人员")
    @ApiModelProperty(value = "签约人员", name = "signUser")
    private String signUser;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "签约日期", name = "signDate")
    private String signDate;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "审核时间", name = "checkDate")
    private String checkDate;

    /** 合同开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同开始日期", name = "beginDate")
    private String beginDate;

    /** 合同结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同结束日期", name = "endDate")
    private String endDate;

    /** 合同附件 */
    @ApiModelProperty(value = "合同附件", name = "file")
    private String file;

    /** 合同附件 */
    @ApiModelProperty(value = "合同附件", name = "file")
    private String terminateFile;

    /** 合同图片 */
    @ApiModelProperty(value = "合同图片", name = "imgs")
    private String imgs;

    /** 合同解约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同解约日期", name = "terminateDate")
    private String terminateDate;

    /** 签约日期 */
    @ApiModelProperty(value = "最早签约日期", name = "signDateStart")
    private String signDateStart;

    @ApiModelProperty(value = "最晚签约日期", name = "signDateEnd")
    private String signDateEnd;

    /** 审核日期 */
    @ApiModelProperty(value = "最早审核日期", name = "checkDateStart")
    private String checkDateStart;

    @ApiModelProperty(value = "最晚审核日期", name = "checkDateEnd")
    private String checkDateEnd;


    /** 合同开始日期 */
    @ApiModelProperty(value = "最早合同开始日期", name = "beginDateStart")
    private String beginDateStart;

    @ApiModelProperty(value = "最晚合同开始日期", name = "beginDateEnd")
    private String beginDateEnd;

    /** 合同结束日期 */
    @ApiModelProperty(value = "最早合同结束日期", name = "endDateStart")
    private String endDateStart;

    @ApiModelProperty(value = "最晚合同结束日期", name = "endDateEnd")
    private String endDateEnd;

}
