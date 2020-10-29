package com.merchant.system.service;

import java.util.List;
import com.merchant.system.domain.Customer;
import com.merchant.system.domain.bo.CustomerBO;
import org.apache.ibatis.annotations.Param;

/**
 * 我的客户Service接口
 * 
 * @author hanke
 * @date 2020-10-28
 */
public interface ICustomerService 
{
    /**
     * 查询我的客户
     * 
     * @param id 我的客户ID
     * @return 我的客户
     */
    public Customer selectCustomerById(Integer id);

    /**
     * 查询我的客户列表
     * 
     * @param customer 我的客户
     * @return 我的客户集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 查询我的线索列表
     *
     * @param customer 我的客户
     * @return 我的线索集合
     */
    public List<Customer> selectXiansuoList(Customer customer);

    /**
     * 新增我的客户
     * 
     * @param customer 我的客户
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改我的客户
     * 
     * @param customer 我的客户
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 批量修改我的客户
     *
     * @param customerBO 我的客户
     * @return 结果
     */
    public int updateCustomerByIds(CustomerBO customerBO);

    /**
     * 批量删除我的客户
     * 
     * @param ids 需要删除的我的客户ID
     * @return 结果
     */
    public int deleteCustomerByIds(Integer[] ids);

    /**
     * 删除我的客户信息
     * 
     * @param id 我的客户ID
     * @return 结果
     */
    public int deleteCustomerById(Integer id);

    /**
     * 更换客户负责人相关信息
     * @param customerBO：我的客户
     * @return
     */
    int transferCustomer(CustomerBO customerBO);

    /**
     * 线索升级为客户
     * @param customerBO
     * @return
     */
    int evolveCustomer(CustomerBO customerBO);

    /**
     * 根据手机号判断用户是否存在
     * @param phone
     * @return
     */
    int existCustomer(@Param("phone") String phone);
}
