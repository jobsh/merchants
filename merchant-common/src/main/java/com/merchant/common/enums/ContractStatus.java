package com.merchant.common.enums;

/**
 * 合同各种状态
 * 
 * @author hanke
 */
public enum ContractStatus
{
    UNCHECK("0", "未审核"),
    CHECKED("1", "已审核"),

    EFFECTIVE_EXECUTING("0", "有效执行中"),
    EFFECTIVE_NOT_EXECUTE("1", "有效未执行"),
    EXPIRED_TERMINATION("2", "到期解约"),
    EXPIRED_RENEW("3", "到期续约"),
    UNEXPIRED_TERMINATION("4", "未到期解约"),

    SIGN_NEW("0", "新签"),
    SIGN_RENEW("1", "续签");


    private final String code;
    private final String info;

    ContractStatus(String code, String info)
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
