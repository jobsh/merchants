package com.merchant.system.mapper;

import com.merchant.system.domain.bo.CustomerReportBO;
import com.merchant.system.domain.vo.CustomerFeeReportVO;
import com.merchant.system.domain.vo.CustomerReportVO;
import com.merchant.system.domain.vo.JingyingManagerFeeReportVO;

import java.util.List;

/**
 * @Classname CustomerReportMapper
 * @Description CustomerReportMapper
 * @Date 2020/11/30 16:00
 * @Created by hanke
 */
public interface CustomerReportMapper {

    List<CustomerReportVO> selectCustomerReportList(CustomerReportBO customerReportBO);

    List<CustomerFeeReportVO> selectCustomerFeeReportList(CustomerReportBO customerReportBO);

    List<JingyingManagerFeeReportVO> selectJingyingMannagerFeeReportList(CustomerReportBO customerReportBO);
}
