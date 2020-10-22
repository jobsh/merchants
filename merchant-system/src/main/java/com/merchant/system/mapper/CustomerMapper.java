package com.merchant.system.mapper;

import java.util.List;

import com.merchant.system.domain.Customer;

/**
 * 我的客户Mapper接口
 * 
 * @author hanke
 * @date 2020-10-19
 */
public interface CustomerMapper 
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
     * 删除我的客户
     * 
     * @param id 我的客户ID
     * @return 结果
     */
    public int deleteCustomerById(Integer id);

    /**
     * 批量删除我的客户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerByIds(Integer[] ids);
}
