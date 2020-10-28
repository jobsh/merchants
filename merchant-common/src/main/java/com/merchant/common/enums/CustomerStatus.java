package com.merchant.common.enums;

/**
 * 用户状态
 * 
 * @author hanke
 */
public enum CustomerStatus
{
    /** 1: 客户 */
    OK("1", "正常"),
    /** 0 : 线索 */
    DISABLE("0", "失效");

    private final String code;
    private final String info;

    CustomerStatus(String code, String info)
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
