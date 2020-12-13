package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 合同对象 biz_contract
 * 
 * @author hanke
 * @date 2020-11-03
 */
@Data
public class Contract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同表主键id */
    private Integer id;

    /** 部门id */
    @Excel(name = "部门id")
    private Integer deptId;

    private Integer pid;

    private String rootNum;

    /** 合同编号 */
    @Excel(name = "合同编号")
    private String num;

    /** 合同代码 */
    @Excel(name = "合同代码")
    private String code;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String customerName;

    /** 客户id */
    private Integer customerId;

    /** 客户编号 */
    private String customerNum;

    /** 客户手机号（可以有多个手机号，逗号分隔） */
    @Excel(name = "客户手机号", readConverterExp = "可以有多个手机号，逗号分隔")
    private String customerPhone;

    /** 合同类型(0：新签:1：续签) */
    @Excel(name = "合同类型(0：新签:1：续签)")
    private String type;

    /** 签约产品 */
    @Excel(name = "签约产品")
    private String produce;

    /** 店面或区域名称 */
    @Excel(name = "店面或区域名称")
    private String dianmianName;

    /** 店面数量 */
    @Excel(name = "店面数量")
    private Integer dianmianNum;

    /** 店面所在省份 */
    @Excel(name = "店面省份")
    private String dianmianProvince;

    /** 店面所在城市 */
    @Excel(name = "店面城市")
    private String dianmianCity;

    /** 店面所在区 */
    @Excel(name = "店面所在区")
    private String dianmianDistrict;

    /** 店面详细地址 */
    @Excel(name = "店面地址")
    private String dianmianAddress;

    /** 店面经度 */
    @Excel(name = "店面经度")
    private String dianmianLongitude;

    /** 店面纬度 */
    @Excel(name = "店面维度")
    private String dianmianLatitude;

    /** 保证金 */
    @Excel(name = "保证金")
    private Integer guarantee;

    /** 各种费用 */
    @Excel(name = "各种费用")
    private String fee;

    /** 操作 */
    @Excel(name = "操作")
    private String operation;

    private Integer managerId;

    /** 负责人 */
    @Excel(name = "负责人")
    private String manager;

    /** 签约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signDate;

    private Integer signUserId;

    /** 签约人员 */
    @Excel(name = "签约人员")
    private String signUser;

    /** 审核时间 */
    @Excel(name = "签约人员")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkDate;

    /** 合同开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /** 合同结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同结束日期")
    private Date endDate;

    /** 合同解约日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "解约日期")
    private Date terminateDate;

    /** 生效失效状态 */
    @Excel(name = "生效失效状态")
    private String status;

    /** 审核状态（0:未审核  1:已审核） */
    @Excel(name = "审核状态")
    private String checkStatus;

    /** 合同附件 */
    private String file;

    /** 合同附件 */
    private String terminateFile;

    /** 合同图片 */
    private String imgs;

    /** 部门名 */
    private String deptName;
}
