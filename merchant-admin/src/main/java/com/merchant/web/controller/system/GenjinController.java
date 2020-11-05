package com.merchant.web.controller.system;

import java.io.IOException;
import java.util.List;

import com.merchant.system.domain.bo.GenjinBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
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
import com.merchant.system.domain.Genjin;
import com.merchant.system.service.IGenjinService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;

/**
 * 客户跟进Controller
 * 
 * @author hanke
 * @date 2020-10-29
 */
@Api(value = "客户跟进相关", tags = {"客户跟进相关相关的api接口"})
@RestController
@RequestMapping("/system/genjin")
public class GenjinController extends BaseController
{
    @Autowired
    private IGenjinService genjinService;

    /**
     * 查询客户跟进列表
     */
    @ApiOperation(value = "通过customerId查出跟进列表", notes = "通过customerId查出跟进列表", httpMethod = "GET")
    @GetMapping("/list/{customerId}")
    public TableDataInfo list(
            @ApiParam(name = "customerId", value = "客户id", required = true)
            @PathVariable("customerId") Integer customerId)
    {
        startPage();
        List<Genjin> list = genjinService.selectGenjinList(customerId);
        return getDataTable(list);
    }


    /**
     * 获取客户跟进详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:genjin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(genjinService.selectGenjinById(id));
    }

    /**
     * 新增客户跟进
     */
    @ApiOperation(value = "新增客户跟进", notes = "新增客户跟进", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('system:genjin:add')")
    @Log(title = "客户跟进", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GenjinBO genjinBO)
    {
        try {
            genjinService.insertGenjin(genjinBO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.success("新增跟进成功");
    }

    /**
     * 修改客户跟进
     */
    @PreAuthorize("@ss.hasPermi('system:genjin:edit')")
    @Log(title = "客户跟进", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Genjin genjin)
    {
        return toAjax(genjinService.updateGenjin(genjin));
    }

    /**
     * 删除客户跟进
     */
    @PreAuthorize("@ss.hasPermi('system:genjin:remove')")
    @Log(title = "客户跟进", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(genjinService.deleteGenjinByIds(ids));
    }
}
