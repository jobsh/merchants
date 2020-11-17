package com.merchant.common.enums;

/**
 * 合同操作类型
 * 
 * @author hanke
 */
public enum ContractOperType
{
    /**
     * 录入
     */
    ADD,

    /**
     * 审核
     */
    CHECK,
    /**
     * 反审核
     */
    UNCHECK,
    /**
     * 转移
     */
    TRANSFER,

    /**
     * 解约
     */
    TERMINATE,

    /**
     * 失效
     */
    ABANDON,

    /**
     * 新增回款
     */
    ADD_FEE,

    /**
     * 审核回款
     */
    CHECK_FEE,

    /**
     * 反审核回款
     */
    UNCHECK_FEE,

    /**
     * 合同修改
     */
    MODIFY,

}
