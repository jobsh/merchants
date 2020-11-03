package com.merchant.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.entity.SysUser;
import com.merchant.system.domain.Customer;
import lombok.Data;

import java.util.Date;

/**
 * @Classname DianmianVO
 * @Description TODO
 * @Date 2020/11/2 10:45
 * @Created by hanke
 */
@Data
public class DianmianVO {

    /** 店面表主键id */
    private Integer id;

    /** 店东id */
    private Integer diandongId;

    /** 店东姓名 */
    private String diandongName;

    /** 店东电话 */
    private String diandongPhone;

    /** 管理人id */
    private Long sysUserId;

    /** 所属公司id */
    private Integer companyId;

    /** 门店名称 */
    private String name;

    /** 门店状态 */
    private String status;

    /** 所属省 */
    private String province;

    /** 所属市 */
    private String city;

    /** 所属区 */
    private String district;

    /** 经度 */
    private String longitude;

    /** 纬度 */
    private String latitude;

    /** 店面详细地址 */
    private String address;

    /** 验收结果 */
    private String checkResult;

    /** 开店日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date openDate;

    /** 合同编号 */
    private String contractnum;

    /** 店面类型（0：单店加盟，1：区域加盟） */
    private String type;

    /** 授权区域 */
    private String area;

    /** 闭店/停业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date closeDate;

    /** 暂停营业时长 */
    private Integer closeDays;

    /** 闭店原因 */
    private String closeReason;

    /** 验收信息 */
    private String checkInfo;

    /** 备注 */
    private String remark;

    /** 客户信息 */
    private Customer customer;


}
