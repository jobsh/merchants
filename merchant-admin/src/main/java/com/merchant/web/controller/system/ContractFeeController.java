package com.merchant.web.controller.system;

import java.util.List;

import com.merchant.system.domain.bo.ContractFeeBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.merchant.common.annotation.Log;
import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.enums.BusinessType;
import com.merchant.system.domain.ContractFee;
import com.merchant.system.service.IContractFeeService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;

/**
 * 费用管理Controller
 * 
 * @author hanke
 * @date 2020-11-10
 */
@RestController
@RequestMapping("/fee/feeManager")
public class ContractFeeController extends BaseController
{
    @Autowired
    private IContractFeeService contractFeeService;

    /**
     * 查询费用管理列表
     */
    @PreAuthorize("@ss.hasPermi('fee:feeManager:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContractFeeBO contractFeeBO)
    {
        startPage();
        List<ContractFee> list = contractFeeService.selectContractFeeList(contractFeeBO);
        return getDataTable(list);
    }

    /**
     * 导出费用管理列表
     */
    @PreAuthorize("@ss.hasPermi('fee:feeManager:export')")
    @Log(title = "费用管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ContractFeeBO contractFeeBO)
    {
        List<ContractFee> list = contractFeeService.selectContractFeeList(contractFeeBO);
        ExcelUtil<ContractFee> util = new ExcelUtil<ContractFee>(ContractFee.class);
        return util.exportExcel(list, "feeManager");
    }

    /**
     * 获取费用管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('fee:feeManager:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(contractFeeService.selectContractFeeById(id));
    }

    /**
     * 新增费用管理
     */
    @PreAuthorize("@ss.hasPermi('fee:feeManager:add')")
    @Log(title = "费用管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContractFee contractFee)
    {
        return toAjax(contractFeeService.insertContractFee(contractFee));
    }

    /**
     * 修改费用管理
     */
    @PreAuthorize("@ss.hasPermi('fee:feeManager:edit')")
    @Log(title = "费用管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContractFee contractFee)
    {
        int res = contractFeeService.updateContractFee(contractFee);
        if (res == -1) {
            return AjaxResult.error("该费用已审核不可编辑");
        }
        return toAjax(res);
    }

    /**
     * 删除费用管理
     */
    @PreAuthorize("@ss.hasPermi('fee:feeManager:remove')")
    @Log(title = "费用管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(contractFeeService.deleteContractFeeByIds(ids));
    }

    /**
     * 根据合同contractNum查询费用
     */
    @PreAuthorize("@ss.hasPermi('fee:feeManager:list')")
    @GetMapping(value = "/getFeeByContractNum/{contractNum}")
    public AjaxResult getFeeByContractNum(@PathVariable String contractNum)
    {
        if (StringUtils.isBlank(contractNum)) {
            return AjaxResult.error("参数有误");
        }
        return AjaxResult.success(contractFeeService.getFeeByContractNum(contractNum));
    }

    /**
     * 费用审核
     */
    @PostMapping(value = "/check/")
    public AjaxResult check(@RequestParam String num,@RequestParam String checkDate)
    {
        if (num == null) {
            return AjaxResult.error("费用编号不能为空");
        }
        if (StringUtils.isBlank(checkDate)) {
            return AjaxResult.error("请选择审核日期");
        }
        return toAjax(contractFeeService.checkContractFeeByNum(num, checkDate));
    }

    /**
     * 费用反审核
     */
    @PostMapping(value = "/uncheck/")
    public AjaxResult uncheck(@RequestParam String num)
    {
        if (StringUtils.isBlank(num)) {
            return AjaxResult.error("费用编号不能为空");
        }
        return toAjax(contractFeeService.unCheckContractFeeByNum(num));
    }
}