package com.merchant.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;

/**
 * 我的客户对象 customer
 * 
 * @author hanke
 * @date 2020-10-19
 */
@Data
public class Customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户id */
    private Integer id;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String name;

    /** 客户电话 */
    private String phone;

    /** 客户等级 */
    @Excel(name = "客户等级")
    private Integer level;

    /** 公司 */
    @Excel(name = "公司")
    private Integer companyId;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 跟进状态 */
    @Excel(name = "跟进状态")
    private Integer genjinStatus;

    /** 客户来源 */
    @Excel(name = "客户来源")
    private String resource;

    /** 负责人 */
    private Integer sysUserId;

    /** 标识客户还是线索 （0：线索；1：客户）*/
    private Integer status;
}
