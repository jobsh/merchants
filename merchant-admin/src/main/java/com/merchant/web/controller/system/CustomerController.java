package com.merchant.web.controller.system;

import java.util.List;

import com.merchant.common.constant.HttpStatus;
import com.merchant.system.domain.bo.CustomerBO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.merchant.common.annotation.Log;
import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.enums.BusinessType;
import com.merchant.system.domain.Customer;
import com.merchant.system.service.ICustomerService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;

/**
 * 我的客户Controller
 *
 * @author hanke
 * @date 2020-10-28
 */
@RestController
@RequestMapping("/system")
public class CustomerController extends BaseController {
    @Autowired
    private ICustomerService customerService;

    /**
     * 查询我的客户列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/customer/list")
    public TableDataInfo customerList(Customer customer) {
        startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
        return getDataTable(list);
    }

    /**
     * 查询我的线索列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/xiansuo/list")
    public TableDataInfo xiansuoList(Customer customer) {
        startPage();
        List<Customer> list = customerService.selectXiansuoList(customer);
        return getDataTable(list);
    }


    /**
     * 导出我的客户列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:export')")
    @Log(title = "我的客户", businessType = BusinessType.EXPORT)
    @GetMapping("/customer/export")
    public AjaxResult export(Customer customer) {
        List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 获取我的客户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:query')")
    @GetMapping(value = "/customer/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return AjaxResult.success(customerService.selectCustomerById(id));
    }

    /**
     * 新增我的客户
     */
    @PreAuthorize("@ss.hasPermi('system:customer:add')")
    @Log(title = "我的客户", businessType = BusinessType.INSERT)
    @PostMapping("/customer")
    public AjaxResult add(@RequestBody Customer customer) {
        return toAjax(customerService.insertCustomer(customer));
    }

    /**
     * 修改我的客户
     */
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "我的客户", businessType = BusinessType.UPDATE)
    @PutMapping("/customer")
    public AjaxResult edit(@RequestBody Customer customer) {
        return toAjax(customerService.updateCustomer(customer));
    }

    /**
     * 删除我的客户
     */
    @PreAuthorize("@ss.hasPermi('system:customer:remove')")
    @Log(title = "我的客户", businessType = BusinessType.DELETE)
    @DeleteMapping("/customer/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(customerService.deleteCustomerByIds(ids));
    }

    /**
     * 转移客户
     */
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "转移客户", businessType = BusinessType.UPDATE)
    @PostMapping("/customer/transfer")
    public AjaxResult transferCustomer(@RequestBody CustomerBO customerBO) {

        int result = customerService.transferCustomer(customerBO);
        if (result == -1) {
            AjaxResult.error(HttpStatus.NOT_FIND_SYSUSER, "没有该负责人");
        } else if (result == 0) {
            AjaxResult.error(HttpStatus.ERROR, "更新失败");

        }
        return AjaxResult.success("转移客户成功");

    }

    /**
     * 转成客户
     */
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "线索转为客户", businessType = BusinessType.UPDATE)
    @PostMapping("/customer/evolve")
    public AjaxResult evolveCustomer(@RequestBody CustomerBO customerBO) {

        int result = customerService.evolveCustomer(customerBO);
        if (result == -1) {
            AjaxResult.error(HttpStatus.NOT_FIND_SYSUSER, "没有该负责人");
        } else if (result == 0) {
            AjaxResult.error(HttpStatus.ERROR, "更新失败");

        }
        return AjaxResult.success("转为客户成功");

    }


}
