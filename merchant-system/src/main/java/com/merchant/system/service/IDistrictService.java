package com.merchant.system.service;

import com.merchant.common.bean.DistrictBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname ICityService
 * @Description TODO
 * @Date 2020/11/19 15:18
 * @Created by hanke
 */
public interface IDistrictService {

    /**
     * 根据city_id获得所有地区(map形式)
     * @param cityId
     * @return
     */
    public List<Map<String, Object>> getAllDistrictsByCityId(int cityId);

    /**
     * 根据city_id获得所有地区(对象形式)
     * @param cityId
     * @return
     */
    public List<DistrictBean> getAllDistrictObjectsByCityId(int cityId);

    /**
     * 根据dis_id获得地区(对象形式)
     * @param disId
     * @return
     */
    public DistrictBean getDistrictObjectByDisId(int disId);

}
