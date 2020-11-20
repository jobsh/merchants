package com.merchant.system.service;

import com.merchant.common.bean.CityBean;
import com.merchant.common.bean.DistrictBean;
import com.merchant.common.bean.ProvinceBean;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import java.util.List;
import java.util.Map;

/**
 * @Classname ICityService
 * @Description TODO
 * @Date 2020/11/19 15:18
 * @Created by hanke
 */

public interface IProvinceService {

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
