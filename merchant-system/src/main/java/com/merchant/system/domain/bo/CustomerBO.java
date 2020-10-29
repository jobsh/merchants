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

    /** 前端传来的客户ids */
    private Integer[] ids;
    /** 前端传来的客户手机号 */
    private String[] phones;
    /** 前端传来的负责人手机号 */
    private String managerPhone;

}
