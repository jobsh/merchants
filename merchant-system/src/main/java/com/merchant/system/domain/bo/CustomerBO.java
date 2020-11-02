package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.system.domain.Customer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Classname CustomerBO
 * @Description CustomerBO
 * @Date 2020/10/28 11:32
 * @Created by hanke
 */
@Data
public class CustomerBO extends Customer {

    /** 前端传来的客户ids */
    @ApiModelProperty(value = "客户ids数组", name = "ids", example = "[1,2,3]")
    private Integer[] ids;
    /** 前端传来的客户手机号 */
    @ApiModelProperty(value = "客户手机号，多个手机号用都号分隔", name = "phones")
    private String[] phones;
    /** 前端传来的负责人手机号 */
    @ApiModelProperty(value = "经纪人手机号，多个手机号用逗号分隔", name = "managerPhone")
    private String managerPhone;

    @ApiModelProperty(value = "最早录入时间", name = "inputDateStart")
    private String inputDateStart;

    @ApiModelProperty(value = "最近录入时间", name = "inputDateEnd")
    private String inputDateEnd;

    /** 客户表主键id */
    private Integer id;

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
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

}
