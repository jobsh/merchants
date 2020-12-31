package com.merchant.web.controller.system;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.merchant.common.constant.HttpStatus;
import com.merchant.common.core.domain.model.LoginUser;
import com.merchant.common.enums.CustomerStatus;
import com.merchant.common.utils.DateUtils;
import com.merchant.common.utils.ServletUtils;
import com.merchant.framework.web.service.TokenService;
import com.merchant.system.domain.bo.AddCustomerBO;
import com.merchant.system.domain.bo.CustomerBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.merchant.common.annotation.Log;
import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.enums.BusinessType;
import com.merchant.system.domain.Customer;
import com.merchant.system.service.ICustomerService;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 我的客户Controller
 *
 * @author hanke
 * @date 2020-10-28
 */
@Api(value = "线索和客户相关", tags = {"线索和客户相关的api接口"})
@RestController
@RequestMapping("/system")
public class CustomerController extends BaseController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private TokenService tokenService;
    /**
     * 查询我的客户列表
     */
    @ApiOperation(value = "查询客户", notes = "查询客户", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @PostMapping("/customer/list")
    public TableDataInfo customerList(@RequestBody(required = false) CustomerBO customerBO) {
        startPage();
        List<Customer> list = customerService.selectCustomerList(customerBO);
        return getDataTable(list);
    }

    /**
     * 查询我的线索列表
     */
    @ApiOperation(value = "查寻线索", notes = "查寻线索", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('system:xiansuo:list')")
    @PostMapping("/xiansuo/list")
    public TableDataInfo xiansuoList(@RequestBody(required = false) CustomerBO customerBO) {
        startPage();
        List<Customer> list = customerService.selectXiansuoList(customerBO);
        return getDataTable(list);
    }


    @ApiOperation(value = "客户线索导入", notes = "客户线索导入", httpMethod = "POST")
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:customer:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Customer> util = new ExcelUtil<>(Customer.class);
        List<Customer> userList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = customerService.importCustomer(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 导出我的客户列表
     */
    @ApiOperation(value = "客户线索导出", notes = "客户线索导出", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('system:customer:export')")
    @Log(title = "我的客户", businessType = BusinessType.EXPORT)
    @GetMapping("/customer/export")
    public AjaxResult exportCustomer(CustomerBO customerBO) {
        List<Customer> list = customerService.selectCustomerList(customerBO);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 导出我的线索列表
     */
    @ApiOperation(value = "客户线索导出", notes = "客户线索导出", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('system:xiansuo:export')")
    @Log(title = "我的客户", businessType = BusinessType.EXPORT)
    @GetMapping("/xiansuo/export")
    public AjaxResult exportXiansuo(CustomerBO customerBO) {
        List<Customer> list = customerService.selectXiansuoList(customerBO);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 获取我的客户详细信息
     */
    @ApiOperation(value = "根据id获取客户详情", notes = "根据id获取客户详情", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('system:customer:query')")
    @GetMapping(value = "/customer/{id}")
    public AjaxResult getCustomerById(
            @ApiParam(name = "id", value = "客户id", required = true)
            @PathVariable("id") Integer id) {
        System.out.println("id" + id);
        return AjaxResult.success(customerService.selectCustomerById(id));
    }

    /**
     * 获取我的线索详细信息
     */
    @ApiOperation(value = "根据id获取客户详情", notes = "根据id获取客户详情", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('system:customer:query')")
    @GetMapping(value = "/xiansuo/{id}")
    public AjaxResult getXiansuoById(
            @ApiParam(name = "id", value = "客户id", required = true)
            @PathVariable("id") Integer id) {
        System.out.println("id" + id);
        return AjaxResult.success(customerService.selectXiansuoById(id));
    }

    /**
     * 新增我的客户/线索（取决于前端传的status，0：线索；1：客户）
     */
    @ApiOperation(value = "新增客户", notes = "新增客户", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('system:customer:add')")
    @Log(title = "我的客户", businessType = BusinessType.INSERT)
    @PostMapping("/customer")
    public AjaxResult add(@Validated @RequestBody AddCustomerBO customer) {
        // 根据手机号判断用户是否存在
        String phone = customer.getPhone();
        if (CustomerStatus.DISABLE.equals(customer.getStatus()) && customerService.existXiansuo(phone)) {
            return AjaxResult.error(HttpStatus.EXIST_CUSTOMER,"已存在该线索");
        }
        // 如果是添加客户，手机号精确判重
        if (CustomerStatus.OK.equals(customer.getStatus()) && customerService.existCustomer(phone,customer.getUserId())) {
            return AjaxResult.error(HttpStatus.EXIST_CUSTOMER,"该负责人已有此客户");
        }
        String username = tokenService.getLoginUser(ServletUtils.getRequest()).getUsername();
        customer.setLuruName(username);
        int i = customerService.insertCustomer(customer);
        if (i>0)
            return AjaxResult.success("新增成功");
        return AjaxResult.error("新增失败");
    }

    /**
     * 修改我的客户
     */
    @ApiOperation(value = "修改客户", notes = "修改客户", httpMethod = "PUT")
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "我的客户", businessType = BusinessType.UPDATE)
    @PutMapping("/customer")
    public AjaxResult edit(@Validated @RequestBody AddCustomerBO customerBO) {
        customerBO.setGenjinDate(customerBO.getGenjinDate());
        return toAjax(customerService.updateCustomer(customerBO));
    }

    /**
     * 批量修改我的客户
     */
    @ApiOperation(value = "批量修改我的客户", notes = "批量修改我的客户", httpMethod = "PUT")
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "我的客户", businessType = BusinessType.UPDATE)
    @PutMapping("/customers")
    public AjaxResult editCustomers(@RequestBody CustomerBO customerBO) {
        return toAjax(customerService.updateCustomerByIds(customerBO));
    }

    /**
     * 删除我的客户
     */
    @ApiOperation(value = "批量删除我的客户", notes = "批量删除我的客户", httpMethod = "DELETE")
    @PreAuthorize("@ss.hasPermi('system:customer:remove')")
    @Log(title = "我的客户", businessType = BusinessType.DELETE)
    @DeleteMapping("/customer/{ids}")
    public AjaxResult remove(
            @ApiParam(name = "ids", value = "客户ids数组", required = true)
            @PathVariable Integer[] ids) {
        return toAjax(customerService.deleteCustomerByIds(ids));
    }

    /**
     * 转移客户
     */
    @ApiOperation(value = "批量转移我的客户", notes = "批量转移我的客户", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @Log(title = "转移客户", businessType = BusinessType.UPDATE)
    @PostMapping("/customer/transfer")
    public AjaxResult transferCustomer(@RequestBody CustomerBO customerBO) {

        if (customerBO.getUserId() == null) {
            return AjaxResult.error("请传入正确负责人");
        }

        int result = customerService.transferCustomer(customerBO);
        if (result == -1) {
            return AjaxResult.error(HttpStatus.NOT_FIND_SYSUSER, "没有该负责人");
        } else if (result == -2) {
            return AjaxResult.error(HttpStatus.ERROR, "该客户在您操作前被删除或转为线索");
        } else if (result == -3) {
            AjaxResult.error(HttpStatus.EXIST_CUSTOMER,"该负责人已有此客户");
        } else {
            AjaxResult.error(HttpStatus.ERROR, "转移失败");
        }
        return AjaxResult.success("转移客户成功");

    }

    /**
     * 转成客户
     */
    @ApiOperation(value = "批量线索转为客户", notes = "批量线索转为客户", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('system:xiansuo:edit')")
    @Log(title = "线索转为客户", businessType = BusinessType.UPDATE)
    @PostMapping("/customer/evolve")
    public AjaxResult evolveCustomer(@RequestBody CustomerBO customerBO) {

        int result = customerService.evolveCustomer(customerBO);
        if (result == -1) {
            return AjaxResult.error(HttpStatus.NOT_FIND_SYSUSER, "没有该负责人");
        } else if (result == -3) {
            return AjaxResult.error(HttpStatus.EXIST_CUSTOMER,"该负责人已有此客户");
        } else if (result == 0){
            return AjaxResult.error(HttpStatus.ERROR, "更新失败");
        }
        return AjaxResult.success("转为客户成功");

    }

    /**
     * 通过手机号判断该线索/客户是否存在
     * @param phone
     * @return
     */
    @ApiOperation(value = "通过手机号判断该线索/客户是否存在", notes = "通过手机号判断该线索/客户是否存在", httpMethod = "GET")
    @GetMapping("/customer/exist")
    public AjaxResult existCustomer(
            @ApiParam(name = "phone", value = "客户手机号", required = true)
            @RequestParam String phone,@RequestParam Integer userId) {

        if (customerService.existCustomer(phone, userId)) {
            return AjaxResult.error(HttpStatus.EXIST_CUSTOMER,"该负责人已有此客户");
        }

        return AjaxResult.success();

    }


}
