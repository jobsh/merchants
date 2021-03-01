package com.merchant.system.domain.vo;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.merchant.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @Classname CustomerReportVO
 * @Description TODO
 * @Date 2020/11/30 15:39
 * @Created by hanke
 */
@Data
public class CustomerReportVO extends BaseEntity {

    private String userName;
    private String phonenumber;
    private String deptName;
    private String companyName;
    private String postName;
    private Integer xiansuoNum;
    private Integer customerNum;
    private Integer gjNum;
    private Integer dandianContractNum;
    private Integer quyuContractNum;
    private Integer dandianNum;
    private Integer quyuDianmianNum;
    private Integer dianmianNum;

}
