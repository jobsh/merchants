package com.merchant.web.controller.system;

import com.merchant.common.core.domain.AjaxResult;
import com.merchant.common.utils.SecurityUtils;
import com.merchant.system.domain.vo.ContractDandianAndQuyuVO;
import com.merchant.system.domain.vo.DianmianAndContractAllVO;
import com.merchant.system.domain.vo.DianmianNumVO;
import com.merchant.system.service.IWorkspaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname WorkspaceController
 * @Description TODO
 * @Date 2020/12/24 16:37
 * @Created by hanke
 */
@Api(value = "工作台", tags = {"工作台相关的api接口"})
@RestController
@RequestMapping("workspace")
public class WorkspaceController {

    @Resource
    private IWorkspaceService workspaceService;

    @ApiOperation(value = "店面地图接口", notes = "店面地图接口", httpMethod = "GET")
    @GetMapping("dianmianNum")
    @PreAuthorize("@ss.hasPermi('workspace:above')")
    public AjaxResult selectDianmianNumList(@ApiParam(name = "type", value = "查询时间类型") String type) {
        return AjaxResult.success(workspaceService.selectDianmianNumList(type));
    }

    @ApiOperation(value = "店面合同ALL接口", notes = "店面合同ALL接口", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('workspace:above')")
    @GetMapping("dianmianAndContractAll")
    public AjaxResult dianmianAndContractAllList(@ApiParam(name = "type", value = "查询时间类型") String type) {
        return AjaxResult.success(workspaceService.dianmianAndContractAllList(type));
    }

    @ApiOperation(value = "店面合同单店加盟和区域加盟共用数据接口", notes = "店面合同单店加盟和区域加盟共用数据接口", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('workspace:above')")
    @GetMapping("ContractDandianAndQuyu")
    public AjaxResult selectContractDandianAndQuyuVO(@ApiParam(name = "type", value = "查询时间类型") String type) {
        return AjaxResult.success(workspaceService.selectContractDandianAndQuyuVO(type));
    }

    @ApiOperation(value = "统计报表接口", notes = "统计报表接口", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('workspace:below')")
    @GetMapping("customerWorkspaceList")
    public AjaxResult customerWorkspaceList(@ApiParam(name = "type", value = "查询时间类型") String type, Integer deptId) {
        if (deptId == null){
            deptId = SecurityUtils.getLoginUser().getUser().getDept().getDeptId().intValue();
        }
        return AjaxResult.success(workspaceService.selectCustomerWorkspaceList(type,deptId));
    }

    @ApiOperation(value = "业绩报表接口", notes = "业绩报表接口", httpMethod = "GET")
    @PreAuthorize("@ss.hasPermi('workspace:below')")
    @GetMapping("customerFeeWorkspaceList")
    public AjaxResult customerFeeWorkspaceList(@ApiParam(name = "type", value = "查询时间类型") String type, Integer deptId) {
        if (deptId == null){
            deptId = SecurityUtils.getLoginUser().getUser().getDept().getDeptId().intValue();
        }
        return AjaxResult.success(workspaceService.selectCustomerFeeWorkspaceList(type,deptId));
    }

}
