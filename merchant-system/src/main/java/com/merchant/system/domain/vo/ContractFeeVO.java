package com.merchant.system.domain.vo;

import com.merchant.common.annotation.Excel;
import com.merchant.system.domain.Contract;
import com.merchant.system.domain.ContractFee;
import lombok.Data;

/**
 * @Classname ContractFeeVO
 * @Description TODO
 * @Date 2020/11/13 10:09
 * @Created by hanke
 */
@Data
public class ContractFeeVO extends ContractFee {

    /**  客户姓名 */
    private String customerName;

    /** 客户id */
    private Integer customerId;

    /** 签约产品 */
    private String produce;

    /** 店面或区域名称 */
    private String dianmianName;

    /** 店面数量 */
    private Integer dianmianNum;

    /** 负责人 */
    private String manager;

}
