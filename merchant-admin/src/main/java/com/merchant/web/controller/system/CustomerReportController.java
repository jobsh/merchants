package com.merchant.web.controller.system;

import com.merchant.common.annotation.Log;
import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.core.page.TableDataInfo;
import com.merchant.common.enums.BusinessType;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.system.domain.Customer;
import com.merchant.system.domain.bo.CustomerReportBO;
import com.merchant.system.domain.vo.CustomerFeeReportVO;
import com.merchant.system.domain.vo.CustomerReportVO;
import com.merchant.system.domain.vo.JingyingManagerFeeReportVO;
import com.merchant.system.service.ICustomerReportService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "统计报表导出", notes = "客户线索导出", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('report:customerReport:export')")
    @GetMapping("customerReportList/export")
    public AjaxResult exportCustomerReportList(CustomerReportBO customerReportBO) {
        List<CustomerReportVO> customerReportVOList = customerReportService.selectCustomerReportList(customerReportBO);
        ExcelUtil<CustomerReportVO> util = new ExcelUtil<>(CustomerReportVO.class);
        return util.exportExcel(customerReportVOList, "customerReport");
    }
    @ApiOperation(value = "业绩报表导出", notes = "客户线索导出", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('report:customerFeeReport:export')")
    @GetMapping("/customerFeeReportList/export")
    public AjaxResult exportCustomerFeeReportList(CustomerReportBO customerReportBO) {
        List<CustomerFeeReportVO> customerReportVOList = customerReportService.selectCustomerFeeReportList(customerReportBO);
        ExcelUtil<CustomerFeeReportVO> util = new ExcelUtil<>(CustomerFeeReportVO.class);
        return util.exportExcel(customerReportVOList, "customerReport");
    }
    @ApiOperation(value = "应收报表导出", notes = "客户线索导出", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('report:jingyingFeeReport:export')")
    @GetMapping("jingyingFeeReportList/export")
    public AjaxResult exportJingyingFeeReportList(CustomerReportBO customerReportBO) {
        List<JingyingManagerFeeReportVO> JingyingManagerFeeVOList = customerReportService.selectJingyingMannagerFeeReportList(customerReportBO);
        ExcelUtil<JingyingManagerFeeReportVO> util = new ExcelUtil<>(JingyingManagerFeeReportVO.class);
        return util.exportExcel(JingyingManagerFeeVOList, "customerReport");
    }
}
