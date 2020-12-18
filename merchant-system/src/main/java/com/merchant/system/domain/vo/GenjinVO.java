package com.merchant.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.merchant.common.annotation.Excel;
import com.merchant.common.core.domain.BaseEntity;
import com.merchant.system.domain.Genjin;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 客户跟进对象 biz_genjin
 * 
 * @author hanke
 * @date 2020-10-29
 */
public class GenjinVO extends Genjin
{
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
