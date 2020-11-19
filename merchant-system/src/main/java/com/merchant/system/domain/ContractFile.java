package com.merchant.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 biz_contract_file
 * 
 * @author hanke
 * @date 2020-11-18
 */
public class ContractFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同附件主键id */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String contractNum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String file;

    /** 合同图片 */
    @Excel(name = "合同图片")
    private String image;

    /** 说明 */
    @Excel(name = "说明")
    private String description;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "说明", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputDate;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "说明", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setContractNum(String contractNum) 
    {
        this.contractNum = contractNum;
    }

    public String getContractNum() 
    {
        return contractNum;
    }
    public void setFile(String file) 
    {
        this.file = file;
    }

    public String getFile() 
    {
        return file;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setInputDate(Date inputDate) 
    {
        this.inputDate = inputDate;
    }

    public Date getInputDate() 
    {
        return inputDate;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("contractNum", getContractNum())
            .append("file", getFile())
            .append("image", getImage())
            .append("createBy", getCreateBy())
            .append("description", getDescription())
            .append("inputDate", getInputDate())
            .append("updateDate", getUpdateDate())
            .append("remark", getRemark())
            .toString();
    }
}
