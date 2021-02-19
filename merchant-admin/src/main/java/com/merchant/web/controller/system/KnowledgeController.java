package com.merchant.web.controller.system;

import com.merchant.common.annotation.Log;
import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.core.page.TableDataInfo;
import com.merchant.common.enums.BusinessType;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.system.domain.Knowledge;
import com.merchant.system.service.IKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 知识库Controller
 * 
 * @author hanke
 * @date 2020-12-18
 */
@RestController
@RequestMapping("/system/knowledge")
public class KnowledgeController extends BaseController
{
    @Autowired
    private IKnowledgeService knowledgeService;

    /**
     * 查询知识库列表
     */
    @PreAuthorize("@ss.hasPermi('zhishiku:list')")
    @GetMapping("/list")
    public TableDataInfo list(Knowledge knowledge)
    {
        startPage();
        List<Knowledge> list = knowledgeService.selectKnowledgeList(knowledge);
        return getDataTable(list);
    }

    /**
     * 导出知识库列表
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:export')")
    @Log(title = "知识库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Knowledge knowledge)
    {
        List<Knowledge> list = knowledgeService.selectKnowledgeList(knowledge);
        ExcelUtil<Knowledge> util = new ExcelUtil<Knowledge>(Knowledge.class);
        return util.exportExcel(list, "knowledge");
    }

    /**
     * 获取知识库详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(knowledgeService.selectKnowledgeById(id));
    }

    /**
     * 新增知识库
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:add')")
    @Log(title = "知识库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Knowledge knowledge)
    {
        return toAjax(knowledgeService.insertKnowledge(knowledge));
    }

    /**
     * 修改知识库
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:edit')")
    @Log(title = "知识库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Knowledge knowledge)
    {
        return toAjax(knowledgeService.updateKnowledge(knowledge));
    }

    /**
     * 删除知识库
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:logicDelete')")
    @Log(title = "知识库", businessType = BusinessType.DELETE)
    @GetMapping("logicDelete/{id}")
    public AjaxResult logicDelete(@PathVariable Integer id)
    {
        return toAjax(knowledgeService.logicDeleteKnowledgeById(id));
    }

    /**
     * 删除知识库
     */
    @PreAuthorize("@ss.hasPermi('system:knowledge:forbidden')")
    @Log(title = "知识库", businessType = BusinessType.DELETE)
    @GetMapping("forbidden")
    public AjaxResult forbiddenKnowledgeById(@RequestParam String status, @RequestParam Integer id)
    {
        return toAjax(knowledgeService.forbiddenKnowledgeById(status,id));
    }
}
