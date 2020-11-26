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
import com.merchant.common.core.domain.entity.SysCompany;
import com.merchant.system.service.ISysCompanyService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;

/**
 * 公司Controller
 * 
 * @author hanke
 * @date 2020-11-25
 */
@RestController
@RequestMapping("/system/company")
public class SysCompanyController extends BaseController
{
    @Autowired
    private ISysCompanyService sysCompanyService;

    /**
     * 查询公司列表
     */
    @PreAuthorize("@ss.hasPermi('system:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCompany sysCompany)
    {
        startPage();
        List<SysCompany> list = sysCompanyService.selectSysCompanyList(sysCompany);
        return getDataTable(list);
    }

    /**
     * 导出公司列表
     */
    @PreAuthorize("@ss.hasPermi('system:company:export')")
    @Log(title = "公司", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysCompany sysCompany)
    {
        List<SysCompany> list = sysCompanyService.selectSysCompanyList(sysCompany);
        ExcelUtil<SysCompany> util = new ExcelUtil<SysCompany>(SysCompany.class);
        return util.exportExcel(list, "company");
    }

    /**
     * 获取公司详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:company:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(sysCompanyService.selectSysCompanyById(id));
    }

    /**
     * 新增公司
     */
    @PreAuthorize("@ss.hasPermi('system:company:add')")
    @Log(title = "公司", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCompany sysCompany)
    {
        return toAjax(sysCompanyService.insertSysCompany(sysCompany));
    }

    /**
     * 修改公司
     */
    @PreAuthorize("@ss.hasPermi('system:company:edit')")
    @Log(title = "公司", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCompany sysCompany)
    {
        return toAjax(sysCompanyService.updateSysCompany(sysCompany));
    }

    /**
     * 删除公司
     */
    @PreAuthorize("@ss.hasPermi('system:company:remove')")
    @Log(title = "公司", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(sysCompanyService.deleteSysCompanyByIds(ids));
    }
}
