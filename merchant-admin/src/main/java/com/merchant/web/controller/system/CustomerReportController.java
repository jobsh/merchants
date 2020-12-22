package com.merchant.web.controller.system;

import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.page.TableDataInfo;
import com.merchant.system.domain.bo.CustomerReportBO;
import com.merchant.system.domain.vo.CustomerFeeReportVO;
import com.merchant.system.domain.vo.CustomerReportVO;
import com.merchant.system.domain.vo.JingyingManagerFeeReportVO;
import com.merchant.system.service.ICustomerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname CustomerReportController
 * @Description CustomerReportController
 * @Date 2020/11/30 16:09
 * @Created by hanke
 */
@RestController
@RequestMapping("customerReport")
public class CustomerReportController extends BaseController {

    @Autowired
    private ICustomerReportService customerReportService;

    @PostMapping("list")
    public TableDataInfo customerReportList(@RequestBody(required = false) CustomerReportBO customerReportBO) {
        startPage();
        List<CustomerReportVO> customerReportVOList = customerReportService.selectCustomerReportList(customerReportBO);
        return getDataTable(customerReportVOList);
    }

    @PostMapping("feeList")
    public TableDataInfo customerFeeReportList(@RequestBody(required = false) CustomerReportBO customerReportBO) {
        startPage();
        List<CustomerFeeReportVO> customerReportVOList = customerReportService.selectCustomerFeeReportList(customerReportBO);
        return getDataTable(customerReportVOList);
    }

    @PostMapping("jingyingFeeList")
    public TableDataInfo jingyingFeeReportList(@RequestBody(required = false) CustomerReportBO customerReportBO) {
        startPage();
        List<JingyingManagerFeeReportVO> JingyingManagerFeeVOList = customerReportService.selectJingyingMannagerFeeReportList(customerReportBO);
        return getDataTable(JingyingManagerFeeVOList);
    }
}
