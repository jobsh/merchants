package com.merchant.common.enums;

/**
 * 用户状态
 * 
 * @author hanke
 */
public enum SysDeptType
{
    IS_DEPT("0", "部门"),
    IS_COMPANY("1", "公司");

    private final String code;
    private final String info;

    SysDeptType(String code, String info)
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
