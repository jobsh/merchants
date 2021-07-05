package com.merchant.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.entity.SysUser;
import com.merchant.system.domain.Customer;
import com.merchant.system.domain.Dianmian;
import lombok.Data;

import java.util.Date;

/**
 * @Classname DianmianVO
 * @Description DianmianVO
 * @Date 2020/11/2 10:45
 * @Created by hanke
 */
@Data
public class DianmianVO extends Dianmian {

    /** 店面表主键id */
    @Excel(name = "店面id")
    private Integer id;

    /** 授权区域 */
    private String areaName;

    /** 店东id */
    private Integer diandongId;

    /** 店东姓名 */
    @Excel(name = "店东姓名")
    private String diandongName;

    /** 店东电话 */
    @Excel(name = "店东电话")
    private String diandongPhone;

    /** 管理人id */
    private Long userId;

    @Excel(name = "负责人姓名")
    private String userName;

    /** 所属公司id */
    private Integer companyId;

    /** 部门name */
    @Excel(name = "部门名称")
    private String deptName;

    /** 所属公司name */
    @Excel(name = "公司名称")
    private String companyName;

    /** 门店名称 */
    @Excel(name = "店面名称")
    private String name;

    /** 门店状态 */
    @Excel(name = "店面状态", readConverterExp = "0=闭店,1=营业中,2=暂停营业,3=开店")
    private String status;

    /** 合同编号 */
    private String contractnum;

    /** 手输合同编号 */
    @Excel(name = "合同编号")
    private String contractCode;

    /** 所属省 */
    @Excel(name = "省")
    private String province;

    /** 所属市 */
    @Excel(name = "市")
    private String city;

    /** 所属区 */
    @Excel(name = "区")
    private String district;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    /** 店面详细地址 */
    @Excel(name = "店面地址")
    private String address;

    /** 验收结果 */
    private String checkResult;

    /** 开店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date setDate;

    /** 营业日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "营业日期",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date openDate;

    /** 闭店/停业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "暂停停业时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date restDate;

    /** 闭店/停业时间 */
    @Excel(name = "闭店时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date closeDate;

    /** 店面类型（0：单店加盟，1：区域加盟） */
    @Excel(name = "店面类型", readConverterExp = "0=单店加盟,1=区域加盟,2=并购")
    private String type;

    /** 授权区域 */
    @Excel(name = "授权区域")
    private String area;

    /** 暂停营业时长 */
    private Integer closeDays;

    /** 闭店原因 */
    @Excel(name = "闭店原因")
    private String closeReason;

    /** 验收信息 */
    private String checkInfo;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 客户信息 */
//    private Customer customer;
    private Integer customerId;

    private String customerName;



}
