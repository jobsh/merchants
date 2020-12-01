package com.merchant.system.domain.bo;

import com.google.common.net.InternetDomainName;
import com.merchant.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @Classname CustomerReportBO
 * @Description TODO
 * @Date 2020/12/1 10:14
 * @Created by hanke
 */
@Data
public class CustomerReportBO extends BaseEntity {

    /** 前端传来的时间 */
    private String inputDate;
    /** 前端传来的部门/公司id */
    private Integer deptId;
    /** 前端传来的用户id */
    private Integer userId;

}
