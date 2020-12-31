package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 店面日志对象 biz_dianmian_log
 * 
 * @author hanke
 * @date 2020-11-26
 */
@Data
public class DianmianLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 店面日志id */
    private Integer id;

    /** 店面id */
    @Excel(name = "店面id")
    private Integer dianmianId;

    /** 具体操作（开店闭店） */
    @Excel(name = "具体操作", readConverterExp = "开=店闭店")
    private String oper;

    /** 描述（闭店原因） */
    @Excel(name = "描述", readConverterExp = "闭=店原因")
    private String discription;

    /** 门店状态 */
    @Excel(name = "门店状态")
    private String status;

    /** 修改店面状态时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改店面状态时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operDate;

    /** 插入时间 */
    private Date inputDate;
}
