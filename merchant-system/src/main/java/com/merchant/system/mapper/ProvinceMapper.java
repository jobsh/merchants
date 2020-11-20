package com.merchant.system.mapper;

import com.merchant.common.bean.ProvinceBean;

import java.util.List;
import java.util.Map;

/**
 * @Classname ProvinceMapper
 * @Description TODO
 * @Date 2020/11/19 14:45
 * @Created by hanke
 */
public interface ProvinceMapper {

    /**
     * 获得所有省份(map形式)
     * @return
     */
    List<Map<String, Object>> getAllProvinces();

    /**
     * 获得所有省份(对象形式)
     * @return
     */
    public List<ProvinceBean> getAllProvinceObjects();

    /**
     * 获得省份(对象形式)
     * @return
     */
    public ProvinceBean getProvinceObjectByProId(int proId);

}
