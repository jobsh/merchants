package com.merchant.web.controller.system;

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
import com.merchant.common.annotation.Log;
import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.enums.BusinessType;
import com.merchant.system.domain.CustomerXiansuo;
import com.merchant.system.service.ICustomerXiansuoService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;

/**
 * 客户线索Controller
 * 
 * @author ruoyi
 * @date 2020-10-19
 */
@RestController
@RequestMapping("/system/xiansuo")
public class CustomerXiansuoController extends BaseController
{
    @Autowired
    private ICustomerXiansuoService customerXiansuoService;

    /**
     * 查询客户线索列表
     */
    @PreAuthorize("@ss.hasPermi('system:xiansuo:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerXiansuo customerXiansuo)
    {
        startPage();
        List<CustomerXiansuo> list = customerXiansuoService.selectCustomerXiansuoList(customerXiansuo);
        return getDataTable(list);
    }

    /**
     * 导出客户线索列表
     */
    @PreAuthorize("@ss.hasPermi('system:xiansuo:export')")
    @Log(title = "客户线索", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CustomerXiansuo customerXiansuo)
    {
        List<CustomerXiansuo> list = customerXiansuoService.selectCustomerXiansuoList(customerXiansuo);
        ExcelUtil<CustomerXiansuo> util = new ExcelUtil<CustomerXiansuo>(CustomerXiansuo.class);
        return util.exportExcel(list, "xiansuo");
    }

    /**
     * 获取客户线索详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:xiansuo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(customerXiansuoService.selectCustomerXiansuoById(id));
    }

    /**
     * 新增客户线索
     */
    @PreAuthorize("@ss.hasPermi('system:xiansuo:add')")
    @Log(title = "客户线索", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerXiansuo customerXiansuo)
    {
        return toAjax(customerXiansuoService.insertCustomerXiansuo(customerXiansuo));
    }

    /**
     * 修改客户线索
     */
    @PreAuthorize("@ss.hasPermi('system:xiansuo:edit')")
    @Log(title = "客户线索", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerXiansuo customerXiansuo)
    {
        return toAjax(customerXiansuoService.updateCustomerXiansuo(customerXiansuo));
    }

    /**
     * 删除客户线索
     */
    @PreAuthorize("@ss.hasPermi('system:xiansuo:remove')")
    @Log(title = "客户线索", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerXiansuoService.deleteCustomerXiansuoByIds(ids));
    }
}
