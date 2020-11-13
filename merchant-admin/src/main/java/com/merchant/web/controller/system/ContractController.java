package com.merchant.web.controller.system;

import java.util.List;

import com.merchant.common.annotation.ContractLog;
import com.merchant.common.utils.DateUtils;
import com.merchant.common.utils.StringUtils;
import com.merchant.system.domain.bo.ContractBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@Api(value = "合同相关相关的api接口", tags = {"合同相关相关的api接口"})
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
     * 查询关联合同
     */
    @GetMapping("/relatedList/{rootNum}")
    public TableDataInfo relatedList(@PathVariable("rootNum") String rootNum)
    {
        startPage();
        List<Contract> list = contractService.selectContractByRootNum(rootNum);
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

    @ApiOperation(value = "修改合同", notes = "修改合同", httpMethod = "PUT")
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
    @ApiOperation(value = "删除合同", notes = "删除合同", httpMethod = "DELETE")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:remove')")
    @Log(title = "合同", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(
            @ApiParam(name = "ids", value = "合同ids", required = true)
            @PathVariable Integer[] ids)
    {
        return toAjax(contractService.deleteContractByIds(ids));
    }


    /**
     * 合同解约
     */
    @ApiOperation(value = "合同解约", notes = "合同解约", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PostMapping("/terminate/{id}")
    public AjaxResult terminate(
            @ApiParam(name = "id", value = "合同id", required = true)
            @PathVariable Integer id) {

        if (id == null) {
            return AjaxResult.error("id错误");
        }

        return toAjax(contractService.terminate(id));
    }


    /**
     * 合同续约
     */
    @ApiOperation(value = "合同续约", notes = "合同续约", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PostMapping("/renew/{id}")
    public AjaxResult renew(
            @ApiParam(name = "id", value = "合同id", required = true)
            @PathVariable Integer id, @RequestBody ContractBO contractBO) {
        return toAjax(contractService.renew(id, contractBO));
    }

    /**
     * 转移合同
     */
    @ApiOperation(value = "转移合同", notes = "转移合同", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @ContractLog()
    @GetMapping("/transfer/")
    public AjaxResult transfer(
            @ApiParam(name = "id", value = "合同id", required = true)
            @RequestParam Integer id,
            @ApiParam(name = "managerId", value = "负责人id", required = true)
            @RequestParam Integer managerId) {

        // 查询合同
        if (managerId == null) {
            return AjaxResult.error("参数错误");
        }

        return toAjax(contractService.transfer(id, managerId));
    }

    /**
     * 审核合同，修改审核状态和签约日期
     * @param id 合同id
     * @param signDate 签约日期
     * @return
     */
    @ApiOperation(value = "审核合同", notes = "审核合同", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @ContractLog()
    @PostMapping("/check/")
    public AjaxResult check(
            @ApiParam(name = "id", value = "合同id", required = true) @RequestParam Integer id,
            @ApiParam(name = "signDate", value = "签约日期", required = true) @RequestParam String signDate) {

        // 查询合同
        if (id == null) {
            return AjaxResult.error("参数错误");
        }
        if (StringUtils.isBlank(signDate)) {
            signDate = DateUtils.dateTimeNow();
        }

        return toAjax(contractService.check(id, signDate));
    }

    /**
     * 反审核
     * @param id 合同id
     * @return
     */
    @ApiOperation(value = "反审核", notes = "反审核", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @ContractLog()
    @GetMapping("/uncheck/{id}")
    public AjaxResult uncheck(@ApiParam(name = "id", value = "合同id", required = true) @PathVariable("id") Integer id) {

        // 查询合同
        if (id == null) {
            return AjaxResult.error("参数错误");
        }

        return toAjax(contractService.uncheck(id));
    }
}
