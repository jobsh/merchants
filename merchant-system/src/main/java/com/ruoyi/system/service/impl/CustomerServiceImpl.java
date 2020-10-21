package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CustomerMapper;
import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.service.ICustomerService;

/**
 * 我的客户Service业务层处理
 * 
 * @author hanke
 * @date 2020-10-21
 */
@Service
public class CustomerServiceImpl implements ICustomerService 
{
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询我的客户
     * 
     * @param id 我的客户ID
     * @return 我的客户
     */
    @Override
    public Customer selectCustomerById(Integer id)
    {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询我的客户列表
     * 
     * @param customer 我的客户
     * @return 我的客户
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer)
    {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增我的客户
     * 
     * @param customer 我的客户
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer)
    {
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改我的客户
     * 
     * @param customer 我的客户
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer)
    {
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除我的客户
     * 
     * @param ids 需要删除的我的客户ID
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Integer[] ids)
    {
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除我的客户信息
     * 
     * @param id 我的客户ID
     * @return 结果
     */
    @Override
    public int deleteCustomerById(Integer id)
    {
        return customerMapper.deleteCustomerById(id);
    }
}
