package com.merchant.system.domain.bo;

import com.merchant.system.domain.Customer;
import lombok.Data;
import sun.rmi.server.InactiveGroupException;

/**
 * @Classname CustomerBO
 * @Description CustomerBO
 * @Date 2020/10/28 11:32
 * @Created by hanke
 */
@Data
public class CustomerBO extends Customer {

    /** 客户id */
    private Integer id;
    /** 负责人手机号 */
    private String managerPhone;

}
