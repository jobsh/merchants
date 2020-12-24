package com.merchant.common.enums;

/**
 * @Classname GenjinStatus
 * @Description TODO
 * @Date 2020/12/23 10:45
 * @Created by hanke
 */
public enum GenjinStatus {
    /** 1: 客户 */
    NOT_PROCESS("0", "未处理"),
    TRACE("1", "跟进"),
    VISITED("2","到访"),
    INTENTION("3","意向"),
    GIVE_PRICE("4","报价"),
    DEAL("5","成交"),
    LAY_ASIDE("6","暂时搁置"),
    /** 0 : 线索 */
    TERMINATED("7", "解约"),
    DISABLET("8", "无效");

    private final String code;
    private final String info;

    GenjinStatus(String code, String info)
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
