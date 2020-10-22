package com.merchant.system.service;

import java.util.List;

import com.merchant.system.domain.Customer;

/**
 * 我的客户Service接口
 * 
 * @author hanke
 * @date 2020-10-21
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
}
