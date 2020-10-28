package com.merchant.system.service.impl;

import java.util.List;

import com.merchant.common.core.domain.entity.SysUser;
import com.merchant.common.core.domain.model.LoginUser;
import com.merchant.common.enums.CustomerStatus;
import com.merchant.common.utils.ServletUtils;
import com.merchant.common.utils.spring.SpringUtils;
import com.merchant.system.domain.bo.CustomerBO;
import com.merchant.system.mapper.SysUserMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.CustomerMapper;
import com.merchant.system.domain.Customer;
import com.merchant.system.service.ICustomerService;
/**
 * 我的客户Service业务层处理
 * 
 * @author hanke
 * @date 2020-10-28
 */
@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService 
{
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private SysUserMapper sysUserMapper;
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
        customer.setStatus(CustomerStatus.OK.getCode());
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 查询我的线索列表
     *
     * @param customer 我的客户
     * @return 我的线索集合
     */

    @Override
    public List<Customer> selectXiansuoList(Customer customer) {
        customer.setStatus(CustomerStatus.DISABLE.getCode());
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

    @Override
    public int transferCustomer(CustomerBO customerBO) {
        // 根据负责人phone 查询出负责人id和负责人name
        SysUser sysUser = new SysUser();
        sysUser.setPhonenumber(customerBO.getManagerPhone());
        // 获取要转移给的人的User信息
        List<SysUser> sysUsers = sysUserMapper.selectUserList(sysUser);
        Customer customer = customerMapper.selectCustomerById(customerBO.getId());
        if (!sysUsers.isEmpty()){
            sysUser = sysUsers.get(0);
            customerBO.setUserId(sysUser.getId().intValue());
            customerBO.setUsername(sysUser.getUserName());
            customer.setStatus(CustomerStatus.OK.getCode());
            // 更新customer负责人id和username

            int result = customerMapper.updateCustomer(customerBO);
            if (result > 0){
                log.info("客户：{}由{}名下转移到{}名下",customer.getName(),customer.getUsername(),sysUser.getUserName());
                return result;
            } else
                return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int evolveCustomer(CustomerBO customerBO) {
        // 清理冗余线索（phone相同的线索为冗余线索）,只保留一条最新更改的线索
        customerMapper.clearRedundantXiansuo(customerBO.getPhone());
        // 修改客户表status为线索状态（status由0置为1）
        customerBO.setStatus(CustomerStatus.OK.getCode());
        // 设置负责人用户名
        return customerMapper.updateCustomer(customerBO);
    }
}
