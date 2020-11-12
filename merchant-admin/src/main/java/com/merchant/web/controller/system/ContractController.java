package com.merchant.web.controller.system;

import java.util.List;

import com.merchant.common.annotation.ContractLog;
import com.merchant.system.domain.bo.ContractBO;
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
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ContractBO contractBO)
    {
        startPage();
        List<Contract> list = contractService.selectContractList(contractBO);
        return getDataTable(list);
    }

    /**
     * 根据客户id查询出客户的合同列表
     */
    @GetMapping("/list/{customerId}")
    public TableDataInfo list(@PathVariable("customerId") Integer customerId)
    {
        startPage();
        List<Contract> list = contractService.selectContractListByCustomerId(customerId);
        return getDataTable(list);
    }


    /**
     * 导出合同列表
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:export')")
    @Log(title = "合同", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ContractBO contractBO)
    {
        List<Contract> list = contractService.selectContractList(contractBO);
        ExcelUtil<Contract> util = new ExcelUtil<>(Contract.class);
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
    public AjaxResult add(@RequestBody ContractBO contractBO)
    {
        return toAjax(contractService.insertContract(contractBO));
    }

    /**
     * 修改合同
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContractBO contractBO)
    {
        return toAjax(contractService.updateContract(contractBO));
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

    /**
     *
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PostMapping("/abandon/{id}")
    public AjaxResult abandon(@PathVariable Integer id) {

        // 查询合同
        if (id == null) {
            return AjaxResult.error("id错误");
        }

        return toAjax(contractService.terminate(id));
    }
    /**
     * 合同解约
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PostMapping("/terminate/{id}")
    public AjaxResult terminate(@PathVariable Integer id) {

        // 查询合同
        if (id == null) {
            return AjaxResult.error("id错误");
        }

        return toAjax(contractService.terminate(id));
    }


    /**
     * 合同续约
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PostMapping("/renew/{id}")
    public AjaxResult renew(@PathVariable Integer id, @RequestBody ContractBO contractBO) {
        return toAjax(contractService.renew(id, contractBO));
    }

    /**
     * 转移合同
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @ContractLog()
    @PostMapping("/transfer/")
    public AjaxResult transfer(Integer managerId) {

        // 查询合同
        if (managerId == null) {
            return AjaxResult.error("参数错误");
        }

        return toAjax(contractService.transfer(managerId));
    }

    /**
     * 审核合同，修改审核状态和签约日期
     * @param id 合同id
     * @param signDate 签约日期
     * @return
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @ContractLog()
    @PostMapping("/check/")
    public AjaxResult check(Integer id,String signDate) {

        // 查询合同
        if (id == null) {
            return AjaxResult.error("参数错误");
        }

        return toAjax(contractService.check(id, signDate));
    }

}
