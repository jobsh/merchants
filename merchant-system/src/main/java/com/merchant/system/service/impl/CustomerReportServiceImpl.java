package com.merchant.system.service.impl;

import com.merchant.common.annotation.DataScope;
import com.merchant.system.domain.bo.CustomerReportBO;
import com.merchant.system.domain.vo.CustomerFeeReportVO;
import com.merchant.system.domain.vo.CustomerReportVO;
import com.merchant.system.domain.vo.JingyingManagerFeeReportVO;
import com.merchant.system.mapper.CustomerReportMapper;
import com.merchant.system.service.ICustomerReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname CustomerReportServiceImpl
 * @Description CustomerReportService 实现类
 * @Date 2020/11/30 16:07
 * @Created by hanke
 */
@Service
public class CustomerReportServiceImpl implements ICustomerReportService {

    @Resource
    private CustomerReportMapper customerReportMapper;

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CustomerReportVO> selectCustomerReportList(CustomerReportBO customerReportBO) {
        return customerReportMapper.selectCustomerReportList(customerReportBO);
    }

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CustomerFeeReportVO> selectCustomerFeeReportList(CustomerReportBO customerReportBO) {
        return customerReportMapper.selectCustomerFeeReportList(customerReportBO);
    }

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<JingyingManagerFeeReportVO> selectJingyingMannagerFeeReportList(CustomerReportBO customerReportBO) {
        return customerReportMapper.selectJingyingMannagerFeeReportList(customerReportBO);
    }
}
