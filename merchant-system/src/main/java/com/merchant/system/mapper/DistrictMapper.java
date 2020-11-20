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
     * @param citycode
     * @return
     */
    public List<Map<String, Object>> getAllDistrictsByCityId(int citycode);

    /**
     * 根据city_id获得所有地区(对象形式)
     * @param citycode
     * @return
     */
    public List<DistrictBean> getAllDistrictObjectsByCityId(int citycode);

    /**
     * 根据dis_id获得地区(对象形式)
     * @param code
     * @return
     */
    public DistrictBean getDistrictObjectByDisId(int code);

}
