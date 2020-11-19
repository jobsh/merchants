package com.merchant.common.enums;

/**
 * 合同各种状态
 * 
 * @author hanke
 */
public enum ContractFeeStatus
{
    UNCHECK("0", "未审核"),
    CHECKED("1", "已审核"),
    ABANDON("2", "失效");

    private final String code;
    private final String info;

    ContractFeeStatus(String code, String info)
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
