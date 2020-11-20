package com.merchant.web.controller.common;

import com.merchant.common.bean.CityBean;
import com.merchant.common.bean.DistrictBean;
import com.merchant.common.bean.ProvinceBean;
import com.merchant.common.core.domain.AjaxResult;
import com.merchant.system.service.ICityService;
import com.merchant.system.service.IDistrictService;
import com.merchant.system.service.IProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Classname AreaController
 * @Description TODO
 * @Date 2020/11/20 8:48
 * @Created by hanke
 */
@Api(value = "省市区接口", tags = {"省市区接口"})
@RestController
@RequestMapping("/common/area")
public class AreaController {

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IDistrictService districtService;

    @ApiOperation(value = "查询所有省份", notes = "查询所有省份", httpMethod = "GET")
    @GetMapping("provinces")
    public AjaxResult getAllProvinces(){
        List<Map<String, Object>> allProvinces = provinceService.getAllProvinces();
        return AjaxResult.success(allProvinces);
    }

    @ApiOperation(value = "查询所有省份返回对象，推荐使用", notes = "查询所有省份返回对象，推荐使用", httpMethod = "GET")
    @GetMapping("provinceObjects")
    public AjaxResult getAllProvinceObjects(){
        List<ProvinceBean> allProvinceObjects = provinceService.getAllProvinceObjects();
        return AjaxResult.success(allProvinceObjects);
    }

    @ApiOperation(value = "通过省份id查询省份", notes = "通过省份id查询省份", httpMethod = "GET")
    @GetMapping("province/{code}")
    public AjaxResult getProvinceObjectByProId(@ApiParam(name = "code", value = "省份code", required = true) @PathVariable Integer code){
        ProvinceBean province = provinceService.getProvinceObjectByProId(code);
        return AjaxResult.success(province);
    }

    @ApiOperation(value = "根据省份id查询城市", notes = "根据省份id查询城市", httpMethod = "GET")
    @GetMapping("citys/{proId}")
    public AjaxResult getAllCitys(@ApiParam(name = "proId", value = "省份id", required = true) @PathVariable Integer proId){
        List<Map<String, Object>> allCitys = cityService.getAllCitiesByProId(proId);
        return AjaxResult.success(allCitys);
    }

    @ApiOperation(value = "根据省份id查询城市，返回对象，推荐使用", notes = "根据省份id查询城市，返回对象，推荐使用", httpMethod = "GET")
    @GetMapping("cityObjects/{proId}")
    public AjaxResult getAllCityObjects(@ApiParam(name = "proId", value = "省份id", required = true) @PathVariable Integer proId){
        List<CityBean> allCityObjects = cityService.getAllCityObjectsByProId(proId);
        return AjaxResult.success(allCityObjects);
    }

    @ApiOperation(value = "根据城市id查询城市", notes = "根据城市id查询城市", httpMethod = "GET")
    @GetMapping("city/{id}")
    public AjaxResult getCityObjectByProId(@ApiParam(name = "id", value = "城市id", required = true) @PathVariable Integer id){
        CityBean cityBean = cityService.getCityObjectByCityId(id);
        return AjaxResult.success(cityBean);
    }

    /**
     * 根据城市id获取该城市的区
     * @param cityId
     * @return
     */
    @ApiOperation(value = "根据城市id查询所有区", notes = "根据城市id查询所有区", httpMethod = "GET")
    @GetMapping("districts/cityId")
    public AjaxResult getAllDistrict(@ApiParam(name = "id", value = "城市id", required = true) @PathVariable Integer cityId){
        List<Map<String, Object>> allDistricts = districtService.getAllDistrictsByCityId(cityId);
        return AjaxResult.success(allDistricts);
    }

    @ApiOperation(value = "根据城市id查询所有区返回对象，推荐使用", notes = "根据城市id查询所有区返回对象，推荐使用", httpMethod = "GET")
    @GetMapping("districtObjects/{cityId}")
    public AjaxResult getAllDistrictObjects(@ApiParam(name = "id", value = "城市id", required = true) @PathVariable Integer cityId){
        List<DistrictBean> districtObjects = districtService.getAllDistrictObjectsByCityId(cityId);
        return AjaxResult.success(districtObjects);
    }

    @ApiOperation(value = "根据区id查询区", notes = "根据区id查询区", httpMethod = "GET")
    @GetMapping("district/{disId}")
    public AjaxResult getDistrictObjectByProId(@ApiParam(name = "id", value = "区id", required = true)@PathVariable Integer disId){
        DistrictBean district = districtService.getDistrictObjectByDisId(disId);
        return AjaxResult.success(district);
    }

}
