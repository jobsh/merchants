package com.merchant.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.merchant.common.annotation.Log;
import com.merchant.common.config.MerchantConfig;
import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.core.domain.model.LoginUser;
import com.merchant.common.core.page.TableDataInfo;
import com.merchant.common.enums.BusinessType;
import com.merchant.common.exception.BaseException;
import com.merchant.common.utils.ServletUtils;
import com.merchant.common.utils.StringUtils;
import com.merchant.common.utils.file.FileUploadUtils;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.framework.web.service.TokenService;
import com.merchant.system.domain.Contract;
import com.merchant.system.domain.bo.AddContractBO;
import com.merchant.system.domain.bo.ContractBO;
import com.merchant.system.service.IContractLogService;
import com.merchant.system.service.IContractService;
import com.merchant.system.service.IDianmianService;
import com.merchant.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 合同Controller
 *
 * @author hanke
 * @date 2020-11-03
 */
@Api(value = "合同相关相关的api接口", tags = {"合同相关相关的api接口"})
@RestController
@RequestMapping("/contract/contractManager")
public class ContractController extends BaseController {
    @Autowired
    private IContractService contractService;
    @Autowired
    private IContractLogService contractLogService;
    @Autowired
    private IDianmianService dianmianService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询合同列表
     */
    @PreAuthorize("@ss.hasPermi('contract:contractManager:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ContractBO contractBO) {
        // 默认查询未失效的,在mapper.xml中控制，已失效状态为2
        startPage();
        List<Contract> list = contractService.selectContractList(contractBO);
        return getDataTable(list);
    }

    /**
     * 根据客户id查询出客户的合同列表
     */
    @GetMapping("/list/{customerId}")
    public TableDataInfo list(@PathVariable("customerId") Integer customerId) {
        startPage();
        List<Contract> list = contractService.selectContractListByCustomerId(customerId);
        return getDataTable(list);
    }

    /**
     * 查询关联合同
     */
    @GetMapping("/relatedList/{rootNum}")
    public TableDataInfo relatedList(@PathVariable("rootNum") String rootNum) {
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
    public AjaxResult export(ContractBO contractBO) {
        List<Contract> list = contractService.selectContractList(contractBO);
        ExcelUtil<Contract> util = new ExcelUtil<>(Contract.class);
        return util.exportExcel(list, "contractManager");
    }

    /**
     * 获取合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('contract:contractitem:list')")
    @GetMapping(value = "/{num}")
    public AjaxResult getInfo(@PathVariable("num") String num) {
        return AjaxResult.success(contractService.selectContractByNum(num));
    }


    /**
     * 新增合同
     */
    @PreAuthorize("@ss.hasPermi('system:customer:renew')")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AddContractBO contractBO) {
        return toAjax(contractService.insertContract(contractBO));
    }

    /**
     * 修改合同
     */

    @ApiOperation(value = "修改合同", notes = "修改合同", httpMethod = "PUT")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContractBO contractBO) {
        int res = 0;

        try {
            res = contractService.updateContract(contractBO);
            if (res == -1) {
                return AjaxResult.error("合同已审核，不可编辑");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toAjax(res);
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
            @PathVariable Integer[] ids) {
        return toAjax(contractService.deleteContractByIds(ids));
    }


    /**
     * 合同解约
     */
    @ApiOperation(value = "合同解约", notes = "合同解约", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:break')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PostMapping("/terminate")
    public AjaxResult terminate(@RequestBody ContractBO contractBO) {

        if (contractBO.getId() == null) {
            return AjaxResult.error("id错误");
        }

        int res;
        try {
            res = contractService.terminate(contractBO);
        } catch (IOException e) {
            throw new BaseException("附件上传异常");
        }
        return toAjax(res);
    }


    /**
     * 合同续约
     */
    @ApiOperation(value = "合同续约", notes = "合同续约", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:renew')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PostMapping("/renew/{id}")
    public AjaxResult renew(
            @ApiParam(name = "id", value = "合同id", required = true)
            @PathVariable Integer id,
            @RequestBody AddContractBO contractBO) {
        int res = contractService.renew(id, contractBO);
        return toAjax(res);
    }

    /**
     * 转移合同
     */
    @ApiOperation(value = "转移合同", notes = "转移合同", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:transfer')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PostMapping("/transfer/ids")
    public AjaxResult transfer(@RequestBody ContractBO contractBO) throws IllegalAccessException {

        // 查询合同
        if (contractBO.getManagerId() == null) {
            return AjaxResult.error("参数错误");
        }

//        SysUser sysUser = userService.selectUserByPhone(contractBO.getManagerPhone());
//        if (sysUser == null) {
//            return AjaxResult.error("无此负责人，请重新输入手机号");
//        }

        return toAjax(contractService.transfer(contractBO.getIds(), contractBO.getManagerId()));
    }

    /**
     * 审核合同，修改审核状态和签约日期
     *
     * @param id        合同id
     * @param checkDate 签约日期
     * @return
     */
    @ApiOperation(value = "审核合同", notes = "审核合同", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:check')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PostMapping("/check/")
    public AjaxResult check(
            @ApiParam(name = "id", value = "合同id", required = true) @RequestParam Integer id,
            @ApiParam(name = "checkDate", value = "签约日期", required = true) @RequestParam String checkDate) throws IllegalAccessException {
        // 查询合同
        if (id == null) {
            return AjaxResult.error("参数错误");
        }
        int res = contractService.check(id, checkDate);
        if (res == -1) {
            return AjaxResult.error("合同已审核，不可失效操作");
        }

        return toAjax(res);
    }

    /**
     * 反审核
     *
     * @param id 合同id
     * @return
     */
    @ApiOperation(value = "反审核", notes = "反审核", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:unCheck')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @GetMapping("/uncheck/{id}")
    public AjaxResult uncheck(@ApiParam(name = "id", value = "合同id", required = true) @PathVariable("id") Integer id) {
        // 查询合同
        if (id == null) {
            return AjaxResult.error("参数错误");
        }

        int res = contractService.uncheck(id);
        if (res == -1) {
            return AjaxResult.error("合同已是未审核状态，无需操作");
        }
        return toAjax(res);
    }

    /**
     * 合同失效
     */
    @ApiOperation(value = "合同失效", notes = "合同失效", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('contract:contractManager:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @GetMapping("/abandon/{id}")
    public AjaxResult abandon(@ApiParam(name = "id", value = "合同id", required = true) @PathVariable("id") Integer id) {

        // 查询合同
        if (id == null) {
            return AjaxResult.error("参数错误");
        }
        int res = contractService.abandon(id);
        if (res == -1) {
            return AjaxResult.error("合同已审核，不可失效操作");
        }

        return toAjax(res);
    }

    /**
     * 手动输入的合同编号判重
     */
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @GetMapping("/existCode/{code}")
    public AjaxResult existCode(@ApiParam(name = "id", value = "手输入合同code", required = true) @PathVariable("code") String code) {

        // 查询合同
        if (code == null) {
            return AjaxResult.error("请输入合同编号");
        }
        int res = contractService.existCode(code);

        if (res > 0) {
            return AjaxResult.error("合同编号已存在，请重新输入");
        }

        return AjaxResult.success();
    }

    /**
     * 上传合同图片
     */
    @PostMapping("/contractImg")
    public AjaxResult uploadContractImage(@RequestBody ContractBO contractBO){
        return toAjax(contractService.uploadContractImgs(contractBO));
    }

    /**
     * 上传合同附件
     */
    @PreAuthorize("@ss.hasPermi('contract:contractItem:addFujian')")
    @PostMapping("/contractFile")
    public AjaxResult uploadContractFile(@RequestParam("id") Integer id, @RequestParam("files") List<MultipartFile> files) throws IOException {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String phonenumber = loginUser.getUser().getPhonenumber();
        if (!files.isEmpty() && files.size() > 0 && files != null) {
            List<String> filePathList = new ArrayList<>();
            for (MultipartFile file : files) {
                String imgPath = FileUploadUtils.upload(MerchantConfig.getContractPath() + "/file/" + phonenumber, file);
                filePathList.add(imgPath);
            }
            if (filePathList != null && filePathList.size() > 0 && !filePathList.isEmpty()) {
                AjaxResult ajax = AjaxResult.success();
                contractService.uploadContractFile(id, JSONObject.toJSONString(filePathList));
                ajax.put("fileUrl", JSONObject.toJSONString(filePathList));
                return ajax;
            }
        }
        return AjaxResult.error("上传附件异常，请联系管理员");
    }


}
