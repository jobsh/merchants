package com.merchant.system.service.impl;

import com.merchant.common.bean.CityBean;
import com.merchant.system.mapper.CityMapper;
import com.merchant.system.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname CityServiceImpl
 * @Description TODO
 * @Date 2020/11/19 15:28
 * @Created by hanke
 */
@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<Map<String, Object>> getAllCitiesByProId(int proId) {
        return cityMapper.getAllCitiesByProId(proId);
    }

    @Override
    public List<CityBean> getAllCityObjectsByProId(int proId) {
        return cityMapper.getAllCityObjectsByProId(proId);
    }

    @Override
    public CityBean getCityObjectByCityId(int cityId) {
        return cityMapper.getCityObjectByCityId(cityId);
    }
}
