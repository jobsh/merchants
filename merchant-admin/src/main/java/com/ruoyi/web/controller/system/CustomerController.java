package com.ruoyi.web.controller.system;

import java.util.List;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.service.ICustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的客户Controller
 * 
 * @author hanke
 * @date 2020-10-21
 */
@RestController
@RequestMapping("/system/customer")
public class CustomerController extends BaseController
{
    @Autowired
    private ICustomerService customerService;

    /**
     * 查询我的客户列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Customer customer)
    {
        startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
        return getDataTable(list);
    }

    /**
     * 导出我的客户列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:export')")
    @Log(title = "我的客户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Customer customer)
    {
        List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 获取我的客户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(customerService.selectCustomerById(id));
    }

    /**
     * 新增我的客户
     */
    @PreAuthorize("@ss.hasPermi('system:customer:add')")
    @Log(title = "我的客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Customer customer)
    {
        return toAjax(customerService.insertCustomer(customer));
    }

    /**
     * 修改我的客户
     */
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "我的客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Customer customer)
    {
        return toAjax(customerService.updateCustomer(customer));
    }

    /**
     * 删除我的客户
     */
    @PreAuthorize("@ss.hasPermi('system:customer:remove')")
    @Log(title = "我的客户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(customerService.deleteCustomerByIds(ids));
    }
}
