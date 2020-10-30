package com.merchant.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.system.domain.Customer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sun.rmi.server.InactiveGroupException;

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
    @ApiModelProperty(value = "经纪人手机号，多个手机号用都号分隔", name = "managerPhone")
    private String managerPhone;

    @ApiModelProperty(value = "最早录入时间", name = "inputDateStart")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inputDateStart;

    @ApiModelProperty(value = "最近录入时间", name = "inputDateEnd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inputDateEnd;


}
