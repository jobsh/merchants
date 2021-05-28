package com.merchant.common.enums;

/**
 * @Classname CustomerResource
 * @Description TODO
 * @Date 2021/5/21 15:08
 * @Created by hanke
 */
public enum CustomerResource {
    TEL("0", "电话开发"),
    STRANGE_VISIT("1", "陌生拜访"),
    REFERRAL("2","转介绍"),
    ADVERTISING_PROMOTION("3","广告推广"),
    OFFICIAL_WEBSITE("4","官网申请"),
    SYSTEM_IMPORT("5","系统导入"),
    CUSTOMER_OVERDUE_MAINTENANCE ("6","客户超期未维护"),
    EXPIRED_CUSTOMER("7","失效客户");

    private final String code;
    private final String info;

    CustomerResource(String code, String info)
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
