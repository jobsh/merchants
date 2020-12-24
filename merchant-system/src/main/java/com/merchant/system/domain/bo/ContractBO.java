package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import com.merchant.system.domain.Contract;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 合同对象 biz_contract
 * 
 * @author hanke
 * @date 2020-11-03
 */
@Data
public class ContractBO extends BaseEntity
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
    private Integer deptId;

    /** 上级合同id */
    private Integer pid;

    @ApiModelProperty(value = "关联合同编号", name = "rootNum")
    private String rootNum;

    /** 合同编号 */
    @ApiModelProperty(value = "合同编号", name = "num")
    private String num;

    /** 合同代码 */
    @ApiModelProperty(value = "合同代码", name = "code")
    private String code;

    /** 客户姓名 */
    @ApiModelProperty(value = "客户姓名", name = "customerName")
    private String customerName;

    /** 客户id */
    @ApiModelProperty(value = "客户id", name = "customerId")
    private Integer customerId;

    @ApiModelProperty(value = "客户编号", name = "customerNum")
    /** 客户编号 */
    private String customerNum;

    /** 客户手机号（可以有多个手机号，逗号分隔） */
    @ApiModelProperty(value = "客户手机号", name = "customerPhone")
    private String customerPhone;

    /** 客户手机号（可以有多个手机号，逗号分隔） */
    @ApiModelProperty(value = "负责人手机号", name = "managerPhone")
    private String managerPhone;

    /** 合同类型(0：新签:1：续签) */
    @Excel(name = "合同类型(0：新签:1：续签)")
    @ApiModelProperty(value = "合同类型(0：新签:1：续签)", name = "type")
    private String type;

    /** 签约产品 */
    @ApiModelProperty(value = "签约产品", name = "produce")
    private String produce;

    /** 店面或区域名称 */
    @ApiModelProperty(value = "店面或区域名称", name = "dianmianName")
    private String dianmianName;

    /** 店面数量 */
    @Excel(name = "店面数量")
    @ApiModelProperty(value = "店面数量，单店加盟默认为1", name = "dianmianNum")
    private Integer dianmianNum;

    /** 店面所在省份 */
    @Excel(name = "店面省份")
    private String dianmianProvince;

    /** 店面所在城市 */
    @Excel(name = "店面城市")
    private String dianmianCity;

    /** 店面所在区 */
    @Excel(name = "店面区")
    private String dianmianDistrict;

    /** 店面详细地址 */
    @Excel(name = "店面详细地址")
    private String dianmianAddress;

    /** 店面经度 */
    @Excel(name = "店面经度")
    private String dianmianLongitude;

    /** 店面纬度 */
    @Excel(name = "店面纬度")
    private String dianmianLatitude;

    /** 保证金 */
    @ApiModelProperty(value = "保证金,单位分显示时需要转化为元", name = "guarantee")
    @Excel(name = "保证金")
    private Integer guarantee;

    /** 各种费用 */
    @ApiModelProperty(value = "各种费用", name = "fee")
    @Excel(name = "各种费用")
    private String fee;

    /** 操作 */
    @Excel(name = "操作")
    @ApiModelProperty(value = "对合同的操作", name = "operation")
    private String operation;

    /** 生效失效状态 */
    @Excel(name = "生效失效状态")
    @ApiModelProperty(value = "生效失效状态", name = "status")
    private String status;

    /** 审核状态（0:未审核  1:已审核） */
    @Excel(name = "审核状态")
    private String checkStatus;

    @Excel(name = "负责人id")
    private Integer managerId;

    /** 负责人 */
    @Excel(name = "负责人")
    @ApiModelProperty(value = "负责人姓名", name = "manager")
    private String manager;

    @Excel(name = "签约人id")
    private Integer signUserId;

    /** 签约人员 */
    @Excel(name = "签约人员")
    @ApiModelProperty(value = "签约人员", name = "signUser")
    private String signUser;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "签约日期", name = "signDate")
    @Excel(name = "签约日期")
    private String signDate;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间")
    @ApiModelProperty(value = "审核时间", name = "checkDate")
    private String checkDate;

    /** 合同开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同开始日期")
    @ApiModelProperty(value = "合同开始日期", name = "beginDate")
    private String beginDate;

    /** 合同结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同结束日期", name = "endDate")
    @Excel(name = "合同结束日期")
    private String endDate;

    /** 合同附件 */
    @ApiModelProperty(value = "合同附件", name = "file")
    @Excel(name = "合同附件")
    private String file;

    /** 合同附件 */
    @ApiModelProperty(value = "合同附件", name = "file")
    @Excel(name = "解约附件")
    private String terminateFile;

    /** 合同图片 */
    @ApiModelProperty(value = "合同图片", name = "imgs")
    @Excel(name = "合同图片")
    private String imgs;

    /** 合同解约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同解约日期", name = "terminateDate")
    @Excel(name = "合同解约日期")
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
