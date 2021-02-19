package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 合同对象 biz_contract
 * 
 * @author hanke
 * @date 2020-11-03
 */
@Data
public class ContractCompareBO
{
    @ApiModelProperty(value = "关联合同编号", name = "rootNum")
    @Excel(name = "关联合同编号")
    private String rootNum;

    /** 合同编号 */
    @ApiModelProperty(value = "合同编号", name = "num")
    @Excel(name = "合同编号")
    private String num;

    /** 合同代码 */
    @ApiModelProperty(value = "合同代码", name = "code")
    @Excel(name = "合同code")
    private String code;

    /** 客户姓名 */
    @ApiModelProperty(value = "客户姓名", name = "customerName")
    @Excel(name = "客户姓名")
    private String customerName;

    /** 客户编号 */
    @ApiModelProperty(value = "客户编号", name = "customerNum")
    @Excel(name = "客户编号")
    private String customerNum;

    /** 客户手机号（可以有多个手机号，逗号分隔） */
    @ApiModelProperty(value = "客户手机号", name = "customerPhone")
    @Excel(name = "客户手机号")
    private String customerPhone;

    /** 合同类型(0：新签:1：续签) */
    @Excel(name = "合同类型(0：新签:1：续签)")
    @ApiModelProperty(value = "合同类型(0：新签:1：续签)", name = "type")
    private String type;

    /** 签约产品 */
    @ApiModelProperty(value = "签约产品", name = "produce")
    @Excel(name = "产品类型")
    private String produce;

    /** 店面或区域名称 */
    @ApiModelProperty(value = "店面或区域名称", name = "dianmianName")
    @Excel(name = "店面或区域名称")
    private String dianmianName;

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

    /** 审核状态（0:未审核  1:已审核） */
    @Excel(name = "审核状态")
    private String checkStatus;

    /** 负责人 */
    @Excel(name = "负责人")
    @ApiModelProperty(value = "负责人姓名", name = "manager")
    private String manager;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "签约日期", name = "signDate")
    @Excel(name = "签约日期")
    private String signDate;


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

}
