package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 biz_contract_file
 * 
 * @author hanke
 * @date 2020-11-18
 */
@Data
public class ContractFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同附件主键id */
    private Long id;

    /** 附件名称 */
    private String name;

    /** 合同编号 */
    private String contractNum;

    /** 文件路径 */
    private String file;

    /** 合同图片 */
    private String image;

    /** 说明 */
    private String description;

    /** 添加合同附件时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inputDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;
}
