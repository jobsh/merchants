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
import com.merchant.system.domain.Constract;
import com.merchant.system.service.IConstractService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;

/**
 * 合同管理Controller
 * 
 * @author hanke
 * @date 2020-11-02
 */
@RestController
@RequestMapping("/constract/constractManager")
public class ConstractController extends BaseController
{
    @Autowired
    private IConstractService constractService;

    /**
     * 查询合同管理列表
     */
    @PreAuthorize("@ss.hasPermi('constract:constractManager:list')")
    @GetMapping("/list")
    public TableDataInfo list(Constract constract)
    {
        startPage();
        List<Constract> list = constractService.selectConstractList(constract);
        return getDataTable(list);
    }

    /**
     * 导出合同管理列表
     */
    @PreAuthorize("@ss.hasPermi('constract:constractManager:export')")
    @Log(title = "合同管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Constract constract)
    {
        List<Constract> list = constractService.selectConstractList(constract);
        ExcelUtil<Constract> util = new ExcelUtil<Constract>(Constract.class);
        return util.exportExcel(list, "constractManager");
    }

    /**
     * 获取合同管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('constract:constractManager:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(constractService.selectConstractById(id));
    }

    /**
     * 新增合同管理
     */
    @PreAuthorize("@ss.hasPermi('constract:constractManager:add')")
    @Log(title = "合同管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Constract constract)
    {
        return toAjax(constractService.insertConstract(constract));
    }

    /**
     * 修改合同管理
     */
    @PreAuthorize("@ss.hasPermi('constract:constractManager:edit')")
    @Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Constract constract)
    {
        return toAjax(constractService.updateConstract(constract));
    }

    /**
     * 删除合同管理
     */
    @PreAuthorize("@ss.hasPermi('constract:constractManager:remove')")
    @Log(title = "合同管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(constractService.deleteConstractByIds(ids));
    }
}
