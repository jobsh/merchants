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
import com.merchant.system.domain.Contract;
import com.merchant.system.service.IContractService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;

/**
 * 合同Controller
 * 
 * @author hanke
 * @date 2020-11-03
 */
@RestController
@RequestMapping("/contract/contractManager")
public class ContractController extends BaseController
{
    @Autowired
    private IContractService contractService;

    /**
     * 查询合同列表
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:list')")
    @GetMapping("/list")
    public TableDataInfo list(Contract contract)
    {
        startPage();
        List<Contract> list = contractService.selectContractList(contract);
        return getDataTable(list);
    }

    /**
     * 导出合同列表
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:export')")
    @Log(title = "合同", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Contract contract)
    {
        List<Contract> list = contractService.selectContractList(contract);
        ExcelUtil<Contract> util = new ExcelUtil<Contract>(Contract.class);
        return util.exportExcel(list, "contractManager");
    }

    /**
     * 获取合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(contractService.selectContractById(id));
    }

    /**
     * 新增合同
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:add')")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Contract contract)
    {
        return toAjax(contractService.insertContract(contract));
    }

    /**
     * 修改合同
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Contract contract)
    {
        return toAjax(contractService.updateContract(contract));
    }

    /**
     * 删除合同
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:remove')")
    @Log(title = "合同", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(contractService.deleteContractByIds(ids));
    }
}
