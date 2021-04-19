package com.merchant.system.domain.vo;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.merchant.common.annotation.Excel;
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

    @Excel(name = "负责人姓名")
    private String userName;
    private String status;
    @Excel(name = "负责人电话")
    private String phonenumber;
    @Excel(name = "负责人部门名称")
    private String deptName;
    @Excel(name = "公司名称")
    private String companyName;
    @Excel(name = "职务")
    private String postName;
    @Excel(name = "新上线索")
    private Integer xiansuoNum;
    @Excel(name = "新上客户")
    private Integer customerNum;
    @Excel(name = "客户跟进")
    private Integer gjNum;
    @Excel(name = "单店加盟数量")
    private Integer dandianContractNum;
    @Excel(name = "区域加盟数量")
    private Integer quyuContractNum;
    @Excel(name = "单店数量")
    private Integer dandianNum;
    @Excel(name = "区域店面数量")
    private Integer quyuDianmianNum;
    @Excel(name = "店面总数")
    private Integer dianmianNum;
}
