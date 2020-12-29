package com.merchant.common.enums;

/**
 * 合同各种状态
 * 
 * @author hanke
 */
public enum DianmianStatus
{
    ClOSED("0", "闭店"),
    OPEN("1", "营业中"),
    REST("2", "暂时停业"),
    SET("3", "开店");

    private final String code;
    private final String info;

    DianmianStatus(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
