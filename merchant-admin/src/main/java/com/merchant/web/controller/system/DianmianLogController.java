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
import com.merchant.system.domain.DianmianLog;
import com.merchant.system.service.IDianmianLogService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;

/**
 * 店面日志Controller
 * 
 * @author hanke
 * @date 2020-11-26
 */
@RestController
@RequestMapping("/dianmian/log")
public class DianmianLogController extends BaseController
{
    @Autowired
    private IDianmianLogService dianmianLogService;

    /**
     * 查询店面日志列表
     */
    @PreAuthorize("@ss.hasPermi('dianmian:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(DianmianLog dianmianLog)
    {
        startPage();
        List<DianmianLog> list = dianmianLogService.selectDianmianLogList(dianmianLog);
        return getDataTable(list);
    }

    /**
     * 导出店面日志列表
     */
    @PreAuthorize("@ss.hasPermi('dianmian:log:export')")
    @Log(title = "店面日志", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DianmianLog dianmianLog)
    {
        List<DianmianLog> list = dianmianLogService.selectDianmianLogList(dianmianLog);
        ExcelUtil<DianmianLog> util = new ExcelUtil<DianmianLog>(DianmianLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 获取店面日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('dianmian:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(dianmianLogService.selectDianmianLogById(id));
    }

    /**
     * 新增店面日志
     */
    @PreAuthorize("@ss.hasPermi('dianmian:log:add')")
    @Log(title = "店面日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DianmianLog dianmianLog)
    {
        return toAjax(dianmianLogService.insertDianmianLog(dianmianLog));
    }

    /**
     * 修改店面日志
     */
    @PreAuthorize("@ss.hasPermi('dianmian:log:edit')")
    @Log(title = "店面日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DianmianLog dianmianLog)
    {
        return toAjax(dianmianLogService.updateDianmianLog(dianmianLog));
    }

    /**
     * 删除店面日志
     */
    @PreAuthorize("@ss.hasPermi('dianmian:log:remove')")
    @Log(title = "店面日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(dianmianLogService.deleteDianmianLogByIds(ids));
    }
}
