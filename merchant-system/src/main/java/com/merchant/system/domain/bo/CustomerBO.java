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

    @ApiModelProperty(value = "关键字（公司、姓名、手机号）模糊搜索", name = "keywords")
    private String keywords;
    /** 客户表主键id */
    @ApiModelProperty(value = "客户表主键id", name = "id")
    private Integer id;

    @ApiModelProperty(value = "店面数量", name = "num")
    private String num;
    /** 客户名称 */
    @ApiModelProperty(value = "客户名称", name = "name")
    private String name;

    /** 客户电话 */
    @ApiModelProperty(value = "客户电话", name = "phone")
    private String phone;

    /** 客户等级 */
    @ApiModelProperty(value = "客户等级", name = "level")
    private String level;

    /** 客户需求 */
    @ApiModelProperty(value = "客户需求", name = "customerNeeds")
    private String customerNeeds;

    /** 公司和部门 */
    @ApiModelProperty(value = "公司和部门", name = "companyName")
    private String companyName;

    /** 省 */
    @ApiModelProperty(value = "省", name = "province")
    private String province;

    /** 市 */
    @ApiModelProperty(value = "市", name = "city")
    private String city;

    /** 区 */
    @ApiModelProperty(value = "区", name = "district")
    private String district;

    /** 门店地址 */
    @ApiModelProperty(value = "门店地址", name = "dianmianAddress")
    private String dianmianAddress;

    /** 客户来源 */
    @Excel(name = "客户来源")
    @ApiModelProperty(value = "客户名称", name = "name")
    private String resource;

    /** 负责人id */
    @ApiModelProperty(value = "负责人id", name = "userId")
    private Integer userId;

    /** 负责人姓名 */
    @ApiModelProperty(value = "负责人姓名", name = "username")
    private String username;

    /** 录入人 */
    @ApiModelProperty(value = "录入人", name = "luruName")
    private String luruName;

    /** 中介经验 */
    @ApiModelProperty(value = "中介经验", name = "experience")
    private String experience;

    /** 客户状态（0：线索，1：客户）  */
    @ApiModelProperty(value = "客户状态", name = "status")
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
