package com.merchant.web.controller.system;

import com.merchant.common.annotation.Log;
import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.core.page.TableDataInfo;
import com.merchant.common.enums.BusinessType;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.system.domain.ContractOperLog;
import com.merchant.system.service.IContractLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录
 * 
 * @author hanke
 */
@RestController
@RequestMapping("/system/contractOperlog")
public class ContractOperlogController extends BaseController
{
    @Autowired
    private IContractLogService operLogService;

    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ContractOperLog operLog)
    {
        startPage();
        List<ContractOperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:export')")
    @GetMapping("/export")
    public AjaxResult export(ContractOperLog operLog)
    {
        List<ContractOperLog> list = operLogService.selectOperLogList(operLog);
        ExcelUtil<ContractOperLog> util = new ExcelUtil<>(ContractOperLog.class);
        return util.exportExcel(list, "操作日志");
    }

    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping("/{operIds}")
    public AjaxResult remove(@PathVariable Long[] operIds)
    {
        return toAjax(operLogService.deleteOperLogByIds(operIds));
    }

    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @PreAuthorize("@ss.hasPermi('monitor:operlog:remove')")
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        operLogService.cleanOperLog();
        return AjaxResult.success();
    }
}
