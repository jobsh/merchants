package com.merchant.system.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Classname Fee
 * @Description TODO
 * @Date 2020/12/23 17:01
 * @Created by hanke
 */
@Data
public class Fee {
    private String lvyueFee;
    private String yunyingManagerFee;
    private String systemUseFee;
    private String systemMaintenanceFee;
    private String daibanFee;
    private String guohuoFee;
    private JingyingManagerFee jingyingManagerFee;

    @Data
    public class JingyingManagerFee {
        private String total;
        private List<Map<String, String>> detail;
    }
}
