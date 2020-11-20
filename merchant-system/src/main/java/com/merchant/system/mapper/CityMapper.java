package com.merchant.system.mapper;

import com.merchant.common.bean.CityBean;

import java.util.List;
import java.util.Map;

/**
 * @Classname CityMapper
 * @Description TODO
 * @Date 2020/11/19 14:45
 * @Created by hanke
 */
public interface CityMapper {

    /**
     * 根据pro_id获得所有城市(map形式)
     * @param proId
     * @return
     */
    List<Map<String, Object>> getAllCitiesByProId(int proId);

    /**
     * 根据pro_id获得所有城市(对象形式)
     * @param proId
     * @return
     */
    public List<CityBean> getAllCityObjectsByProId(int proId);

    /**
     * 根据city_id获得城市(对象形式)
     * @param cityId
     * @return
     */
    public CityBean getCityObjectByCityId(int cityId);

}
