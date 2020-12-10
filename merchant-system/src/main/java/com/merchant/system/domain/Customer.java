package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import com.merchant.common.enums.CustomerStatus;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 我的客户对象 biz_customer
 * 
 * @author hanke
 * @date 2020-10-28
 */
@Data
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户表主键id */
    private Integer id;

    /** 用户部门id */
    private Integer deptId;

    /** 用户部门id */
    private String deptName;

    private String num;
    /** 客户名称 */
    @Excel(name = "客户名称")
    private String name;

    /** 客户电话 */
    @Excel(name = "客户电话")
    private String phone;

    /** 客户等级 */
    @Excel(name = "客户等级")
    private String level;

    /** 客户需求 */
    @Excel(name = "客户需求")
    private String customerNeeds;

    /** 公司和部门 */
    @Excel(name = "公司和部门")
    private String companyName;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 门店地址 */
    @Excel(name = "门店地址")
    private String dianmianAddress;

    /** 客户来源 */
    @Excel(name = "客户来源")
    private String resource;

    /** 负责人id */
    private Integer userId;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String username;

    /** 录入人 */
    @Excel(name = "录入人")
    private String luruName;

    /** 中介经验 */
    @Excel(name = "中介经验")
    private String experience;

    /** 客户状态（0：线索，1：客户）  */
    private String status;

    /** 最新跟进时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date genjinDate;

    /** 最新跟进状态 */
    private String genjinStatus;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;
}
