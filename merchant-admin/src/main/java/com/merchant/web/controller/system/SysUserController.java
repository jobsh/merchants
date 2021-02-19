package com.merchant.web.controller.system;

import java.util.List;
import java.util.stream.Collectors;

import com.merchant.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.merchant.common.annotation.Log;
import com.merchant.common.constant.UserConstants;
import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.core.domain.entity.SysRole;
import com.merchant.common.core.domain.entity.SysUser;
import com.merchant.common.core.domain.model.LoginUser;
import com.merchant.common.core.page.TableDataInfo;
import com.merchant.common.enums.BusinessType;
import com.merchant.common.utils.SecurityUtils;
import com.merchant.common.utils.ServletUtils;
import com.merchant.common.utils.StringUtils;
import com.merchant.common.utils.poi.ExcelUtil;
import com.merchant.framework.web.service.TokenService;
import com.merchant.system.service.ISysPostService;
import com.merchant.system.service.ISysRoleService;
import com.merchant.system.service.ISysUserService;

/**
 * 用户信息
 * 
 * @author hanke
 */
@Api(value = "系统用户相关", tags = {"系统用户相关的api接口"})
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 获取用户列表
     */
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表", httpMethod = "POST")
//    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @ApiOperation(value = "根据关键字获取用户列表", notes = "根据关键字获取用户列表", httpMethod = "GET")
//    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list/keywords")
    public TableDataInfo listByKeywords(@ApiParam(name = "keywords", value = "查询关键字", required = true) @RequestParam(required = false) String keywords)
    {
        startPage();
        List<SysUser> list = userService.selectUserByKeywords(keywords);
        return getDataTable(list);
    }

    @ApiOperation(value = "系统用户导出", notes = "系统用户导出", httpMethod = "GET")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @GetMapping("/export")
    public AjaxResult export(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @ApiOperation(value = "系统用户导入", notes = "系统用户导入", httpMethod = "POST")
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @ApiOperation(value = "系统用户导入模板", notes = "系统用户导入模板", httpMethod = "GET")
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 根据用户id获取详细信息
     */
    @ApiOperation(value = "根据用户id获取详细信息", notes = "根据用户id获取详细信息", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult getInfo(@ApiParam(name = "userId", value = "系统用户id", required = true) @PathVariable(value = "userId", required = false) Long userId)
    {
        AjaxResult ajax = AjaxResult.success();
        SysUser sysUser = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        Long currentUserId = sysUser.getId();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SecurityUtils.isAdmin(sysUser) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @ApiOperation(value = "新增用户", notes = "新增用户", httpMethod = "POST")
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user)
    {
//        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
//        {
//            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
//        }
//        else
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }

        user.setCreateBy(SecurityUtils.getUsername());
        String initPassword = configService.selectConfigByKey("sys.user.initPassword");
        user.setPassword(SecurityUtils.encryptPassword(initPassword));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @ApiOperation(value = "修改用户", notes = "修改用户", httpMethod = "PUT")
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getPassword())) {
            String encryptPassword = SecurityUtils.encryptPassword(user.getPassword());
            user.setPassword(encryptPassword);
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "批量删除用户", notes = "批量删除用户", httpMethod = "DELETE")
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@ApiParam(name = "userIds", value = "系统用户ids", required = true) @PathVariable Long[] userIds)
    {
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @ApiOperation(value = "重置密码", notes = "重置密码", httpMethod = "PUT")
    @PreAuthorize("@ss.hasPermi('system:user:resetPwd')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        int res = userService.resetPwd(user);
        return toAjax(res);
    }

    /**
     * 状态修改
     */
    @ApiOperation(value = "状态修改", notes = "状态修改", httpMethod = "PUT")
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }
}
