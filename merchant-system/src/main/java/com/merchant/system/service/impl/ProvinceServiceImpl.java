package com.merchant.system.service.impl;

import com.merchant.common.bean.ProvinceBean;
import com.merchant.system.mapper.ProvinceMapper;
import com.merchant.system.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname ProvinceServiceImpl
 * @Description TODO
 * @Date 2020/11/19 15:27
 * @Created by hanke
 */
@Service
public class ProvinceServiceImpl implements IProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<Map<String, Object>> getAllProvinces() {
        return provinceMapper.getAllProvinces();
    }

    @Override
    public List<ProvinceBean> getAllProvinceObjects() {
        return provinceMapper.getAllProvinceObjects();
    }

    @Override
    public ProvinceBean getProvinceObjectByProId(int proId) {
        return provinceMapper.getProvinceObjectByProId(proId);
    }
}
