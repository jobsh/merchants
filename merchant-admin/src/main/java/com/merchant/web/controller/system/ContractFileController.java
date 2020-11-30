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
import com.merchant.system.domain.ContractFile;
import com.merchant.system.service.IContractFileService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;

/**
 * 合同附件Controller
 * 
 * @author hanke
 * @date 2020-11-18
 */
@RestController
@RequestMapping("/system/file")
public class ContractFileController extends BaseController
{
    @Autowired
    private IContractFileService contractFileService;

    /**
     * 查询列表
     */
    @PreAuthorize("@ss.hasPermi('system:file:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContractFile contractFile)
    {
        startPage();
        List<ContractFile> list = contractFileService.selectContractFileList(contractFile);
        return getDataTable(list);
    }

    /**
     * 导出
     */
    @PreAuthorize("@ss.hasPermi('system:file:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ContractFile contractFile)
    {
        List<ContractFile> list = contractFileService.selectContractFileList(contractFile);
        ExcelUtil<ContractFile> util = new ExcelUtil<ContractFile>(ContractFile.class);
        return util.exportExcel(list, "file");
    }

    /**
     * 根据id获取合同附件详情
     */
    @PreAuthorize("@ss.hasPermi('system:file:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(contractFileService.selectContractFileById(id));
    }

    /**
     * 新增
     */
    @PreAuthorize("@ss.hasPermi('system:file:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContractFile contractFile)
    {
        return toAjax(contractFileService.insertContractFile(contractFile));
    }

    /**
     * 修改
     */
    @PreAuthorize("@ss.hasPermi('system:file:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContractFile contractFile)
    {
        return toAjax(contractFileService.updateContractFile(contractFile));
    }

    /**
     * 根据ids批量删除
     */
    @PreAuthorize("@ss.hasPermi('system:file:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(contractFileService.deleteContractFileByIds(ids));
    }
}
