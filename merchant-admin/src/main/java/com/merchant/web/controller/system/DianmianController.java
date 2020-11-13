package com.merchant.web.controller.system;

import java.util.List;

import com.merchant.system.domain.vo.DianmianVO;
import io.swagger.annotations.Api;
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
import com.merchant.system.domain.Dianmian;
import com.merchant.system.service.IDianmianService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;

/**
 * 店面管理Controller
 * 
 * @author hanke
 * @date 2020-11-02
 */
@Api(value = "店面相关的api接口", tags = {"店面相关的api接口"})
@RestController
@RequestMapping("/dianmian/dianmianManager")
public class DianmianController extends BaseController
{
    @Autowired
    private IDianmianService dianmianService;

    /**
     * 查询店面管理列表
     */
    @PreAuthorize("@ss.hasPermi('dianmian:dianmianManager:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody Dianmian dianmian)
    {
        startPage();
        List<DianmianVO> list = dianmianService.selectDianmianList(dianmian);
        return getDataTable(list);
    }


    /**
     * 查询店面管理列表
     */
    @PreAuthorize("@ss.hasPermi('dianmian:dianmianManager:list')")
    @GetMapping("/listOfContractNum/{ContractNum}")
    public TableDataInfo listByContractNum(@PathVariable String ContractNum)
    {
        startPage();
        List<DianmianVO> list = dianmianService.selectDianmianByContractNum(ContractNum);
        return getDataTable(list);
    }
    /**
     * 导出店面管理列表
     */
    @PreAuthorize("@ss.hasPermi('dianmian:dianmianManager:export')")
    @Log(title = "店面管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Dianmian dianmian)
    {
        List<DianmianVO> list = dianmianService.selectDianmianList(dianmian);
        ExcelUtil<DianmianVO> util = new ExcelUtil<DianmianVO>(DianmianVO.class);
        return util.exportExcel(list, "dianmianManager");
    }

    /**
     * 获取店面管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('dianmian:dianmianManager:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(dianmianService.selectDianmianById(id));
    }

    /**
     * 新增店面管理
     */
    @PreAuthorize("@ss.hasPermi('dianmian:dianmianManager:add')")
    @Log(title = "店面管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Dianmian dianmian)
    {
        return toAjax(dianmianService.insertDianmian(dianmian));
    }

    /**
     * 修改店面管理
     */
    @PreAuthorize("@ss.hasPermi('dianmian:dianmianManager:edit')")
    @Log(title = "店面管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Dianmian dianmian)
    {
        return toAjax(dianmianService.updateDianmian(dianmian));
    }

    /**
     * 删除店面管理
     */
    @PreAuthorize("@ss.hasPermi('dianmian:dianmianManager:remove')")
    @Log(title = "店面管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(dianmianService.deleteDianmianByIds(ids));
    }
}
