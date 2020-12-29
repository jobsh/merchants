package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 知识库对象 biz_knowledge
 * 
 * @author hanke
 * @date 2020-12-18
 */
@Data
public class Knowledge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 描述 */
    @Excel(name = "描述")
    private String discription;

    /** 知识库地址 */
    @Excel(name = "知识库地址")
    private String url;

    /** 知识库地址 */
    @Excel(name = "是否逻辑删除")
    private String isDelete;

    /** 知识库地址 */
    @Excel(name = "是否禁用")
    private String status;


    /** 插入时间 */
    private Date inputDate;

    /** 更新时间 */
    private Date updateDate;
}
