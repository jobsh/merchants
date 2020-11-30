package com.merchant.web.controller.system;

import com.merchant.common.core.domain.AjaxResult;
import com.merchant.system.domain.vo.CustomerReportVO;
import com.merchant.system.mapper.CustomerReportMapper;
import com.merchant.system.service.ICustomerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname CustomerReportController
 * @Description CustomerReportController
 * @Date 2020/11/30 16:09
 * @Created by hanke
 */
@RestController("customerReport")
public class CustomerReportController {

    @Autowired
    private ICustomerReportService customerReportService;

    @GetMapping("list")
    public AjaxResult selectCustomerReportList() {
        List<CustomerReportVO> customerReportVOList = customerReportService.selectCustomerReportList();
        return AjaxResult.success(customerReportVOList);
    }

}
