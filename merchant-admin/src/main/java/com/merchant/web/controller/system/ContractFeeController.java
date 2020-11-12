package com.merchant.web.controller.system;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
    public TableDataInfo list(ContractFee contractFee)
    {
        startPage();
        List<ContractFee> list = contractFeeService.selectContractFeeList(contractFee);
        return getDataTable(list);
    }

    /**
     * 导出费用管理列表
     */
    @PreAuthorize("@ss.hasPermi('fee:feeManager:export')")
    @Log(title = "费用管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ContractFee contractFee)
    {
        List<ContractFee> list = contractFeeService.selectContractFeeList(contractFee);
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
        return toAjax(contractFeeService.updateContractFee(contractFee));
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
        return toAjax(contractFeeService.getFeeByContractNum(contractNum));
    }
}
