package com.merchant.system.service.impl;

import com.merchant.system.domain.vo.CustomerReportVO;
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
    public List<CustomerReportVO> selectCustomerReportList() {
        return customerReportMapper.selectCustomerReportList();
    }
}
