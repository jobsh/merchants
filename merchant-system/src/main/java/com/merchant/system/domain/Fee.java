package com.merchant.system.domain;

import com.merchant.common.annotation.Excel;
import com.merchant.common.annotation.Excels;
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
    @Excel(name = "履约保证金")
    private String lvyueFee;
    @Excel(name = "经营管理费")
    private String yunyingManagerFee;
    @Excel(name = "系统使用费")
    private String systemUseFee;
    @Excel(name = "系统维护费")
    private String systemMaintenanceFee;
    @Excel(name = "代办费")
    private String daibanFee;
    @Excel(name = "过户费")
    private String guohuoFee;
    @Excel(name = "经营管理费")
    private JingyingManagerFee jingyingManagerFee;

    @Data
    public class JingyingManagerFee {
        @Excel(name = "总费用")
        private String total;
        @Excel(name = "经营管理费详情")
        private List<Map<String, String>> detail;
    }
}
