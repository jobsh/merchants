package com.merchant.system.mapper;

import com.merchant.common.bean.DistrictBean;

import java.util.List;
import java.util.Map;

/**
 * @Classname DistrictMapper
 * @Description TODO
 * @Date 2020/11/19 14:45
 * @Created by hanke
 */
public interface DistrictMapper {

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
