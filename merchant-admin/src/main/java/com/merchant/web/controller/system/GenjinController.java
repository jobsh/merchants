package com.merchant.web.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.merchant.common.annotation.Log;
import com.merchant.common.config.MerchantConfig;
import com.merchant.common.core.controller.BaseController;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.core.domain.model.LoginUser;
import com.merchant.common.core.page.TableDataInfo;
import com.merchant.common.enums.BusinessType;
import com.merchant.common.utils.ServletUtils;
import com.merchant.common.utils.StringUtils;
import com.merchant.common.utils.file.FileUploadUtils;
import com.merchant.framework.web.service.TokenService;
import com.merchant.system.domain.Genjin;
import com.merchant.system.domain.bo.AddGenjinBO;
import com.merchant.system.domain.bo.GenjinBO;
import com.merchant.system.service.IGenjinService;
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
 * 客户跟进Controller
 * 
 * @author hanke
 * @date 2020-10-29
 */
@Api(value = "客户跟进相关", tags = {"客户跟进相关相关的api接口"})
@RestController
@RequestMapping("/system/genjin")
public class GenjinController extends BaseController
{
    @Autowired
    private IGenjinService genjinService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询客户跟进列表
     */
    @ApiOperation(value = "通过customerId查出跟进列表", notes = "通过customerId查出跟进列表", httpMethod = "GET")
    @GetMapping("/list/{customerId}")
    public TableDataInfo list(
            @ApiParam(name = "customerId", value = "客户id", required = true)
            @PathVariable("customerId") Integer customerId)
    {
        startPage();
        List<Genjin> list = genjinService.selectGenjinList(customerId);
        return getDataTable(list);
    }


    /**
     * 获取客户跟进详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:genjin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(genjinService.selectGenjinById(id));
    }

    /**
     * 新增客户跟进
     */
    @ApiOperation(value = "新增客户跟进", notes = "新增客户跟进", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('system:genjin:add')")
    @Log(title = "客户跟进", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AddGenjinBO genjinBO)
    {
        if (StringUtils.isBlank(genjinBO.getMethod())) return AjaxResult.error("请选择跟进方式");
        if (StringUtils.isBlank(genjinBO.getStatus())) return AjaxResult.error("请选择跟进状态");

        int res = 0;

        try {
             genjinService.insertGenjin(genjinBO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (res == -1) {
            return AjaxResult.error("客户或线索不存在");
        }
        return AjaxResult.success("新增跟进成功");
    }

    /**
     *
     * 上传跟进图片
     */
    @PostMapping("/genjinImg")
    public AjaxResult uploadGenjinImager(@RequestParam("imgs") List<MultipartFile> imgs) throws IOException{
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String phonenumber = loginUser.getUser().getPhonenumber();
        if (!imgs.isEmpty() && imgs.size() > 0 && imgs != null) {
            List<String> imgPathList = new ArrayList<>();
            for (MultipartFile img : imgs) {
                String imgPath = FileUploadUtils.upload(MerchantConfig.getGenjinPath() + "/" + phonenumber, img);
                imgPathList.add(imgPath);
            }
            if (imgPathList != null && imgPathList.size() > 0 && !imgPathList.isEmpty()) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", JSONObject.toJSONString(imgPathList));
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }
    /**
     * 修改客户跟进
     */
    @PreAuthorize("@ss.hasPermi('system:genjin:edit')")
    @Log(title = "客户跟进", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GenjinBO genjinBO)
    {
        return toAjax(genjinService.updateGenjin(genjinBO));
    }

    /**
     * 删除客户跟进
     */
    @PreAuthorize("@ss.hasPermi('system:genjin:remove')")
    @Log(title = "客户跟进", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(genjinService.deleteGenjinByIds(ids));
    }
}
