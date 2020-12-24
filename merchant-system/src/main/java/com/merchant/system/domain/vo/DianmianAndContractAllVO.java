package com.merchant.system.domain.vo;

import lombok.Data;

/**
 * @author hanke
 * @date 2020-12-24
 */
@Data
public class DianmianAndContractAllVO
{
    /** 营业店面 */
    private Integer yingyeDianmian;
    /** 累计店面 */
    private Integer allDianmian;
    /** 新签合同 */
    private Integer newContract;
    /** 续签合同 */
    private Integer renewContract;
}
