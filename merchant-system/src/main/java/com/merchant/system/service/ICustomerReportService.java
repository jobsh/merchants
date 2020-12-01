package com.merchant.system.service;

import com.merchant.system.domain.bo.CustomerReportBO;
import com.merchant.system.domain.vo.CustomerReportVO;

import java.util.List;

/**
 * @Classname CustomerReportService
 * @Description CustomerReportService
 * @Date 2020/11/30 16:07
 * @Created by hanke
 */
public interface ICustomerReportService {

    List<CustomerReportVO> selectCustomerReportList(CustomerReportBO customerReportBO);

}
