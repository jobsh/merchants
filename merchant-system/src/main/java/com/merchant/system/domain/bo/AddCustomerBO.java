package com.merchant.system.domain.bo;

import com.merchant.common.annotation.Excel;
import com.merchant.system.domain.Customer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Classname CustomerBO
 * @Description CustomerBO
 * @Date 2020/10/28 11:32
 * @Created by hanke
 */
@Data
public class AddCustomerBO extends Customer {

    /** 部门id */
    @ApiModelProperty(value = "负责人部门id", name = "deptId")
    private Integer deptId;
    /** 前端传来的负责人手机号 */
    @ApiModelProperty(value = "经纪人手机号，多个手机号用逗号分隔", name = "managerPhone")
    @Pattern(regexp = "^1[3456789][0-9]{9}(,1[3456789][0-9]{9})*$", message = "手机号格式不正确")
    private String managerPhone;

    /** 客户表主键id */
    @ApiModelProperty(value = "客户表主键id", name = "id")
    private Integer id;

    @ApiModelProperty(value = "店面数量", name = "num")
    private String num;
    /** 客户名称 */
    @ApiModelProperty(value = "客户名称", name = "name")
    @NotNull(message = "客户姓名不能为空")
    private String name;

    /** 客户电话 */
    @NotNull(message = "客户手机号不能为空")
    @Pattern(regexp = "^1[3456789][0-9]{9}(,1[3456789][0-9]{9})*$", message = "手机号格式不正确")
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
    @ApiModelProperty(value = "客户来源", name = "name")
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

}
