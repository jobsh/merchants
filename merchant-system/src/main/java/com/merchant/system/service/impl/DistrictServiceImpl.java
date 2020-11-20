package com.merchant.system.service.impl;

import com.merchant.common.bean.DistrictBean;
import com.merchant.system.mapper.DistrictMapper;
import com.merchant.system.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname DistrictServiceImpl
 * @Description TODO
 * @Date 2020/11/20 8:46
 * @Created by hanke
 */
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<Map<String, Object>> getAllDistrictsByCityId(int cityId) {
        return districtMapper.getAllDistrictsByCityId(cityId);
    }

    @Override
    public List<DistrictBean> getAllDistrictObjectsByCityId(int cityId) {
        return districtMapper.getAllDistrictObjectsByCityId(cityId);
    }

    @Override
    public DistrictBean getDistrictObjectByDisId(int disId) {
        return districtMapper.getDistrictObjectByDisId(disId);
    }
}
