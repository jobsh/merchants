package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname GenjinBO
 * @Description GenjinBO
 * @Date 2020/10/29 14:58
 * @Created by hanke
 */
@Data
public class AddGenjinBO {

    /** 客户id */
    @ApiModelProperty(value = "客户id", name = "customerId")
    private Integer customerId;

    /** 客户姓名 */
    @ApiModelProperty(value = "客户姓名", name = "customerName")
    private String customerName;

    /** 负责人id */
    @ApiModelProperty(value = "负责人id", name = "userId")
    private Long userId;

    /** 写跟进的内容 */
    @ApiModelProperty(value = "写跟进的内容", name = "content")
    private String content;

    /** 跟进方式 */
    @NotNull(message = "请输入跟进方式")
    @ApiModelProperty(value = "跟进方式（电话、微信、qq...）", name = "method")
    private String method;

    /** 跟进状态(0：跟进；1：到访；2：意向；3：报价；4：成交；5：解约；6：暂时搁置） */
    @ApiModelProperty(value = "跟进状态", name = "status")
    @NotNull(message = "请选择跟进状态")
    private String status;

    /** 写跟进插入的图片 */
    private String image;

    /** 跟进时间,即录入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String genjinDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateDate;
}
