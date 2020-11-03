package com.merchant.system.service;

import java.util.List;

import com.merchant.common.core.domain.entity.SysUser;
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
     * 查询我的线索
     *
     * @param id 我的线索ID
     * @return 我的线索
     */
    public Customer selectXiansuoById(Integer id);

    /**
     * 查询我的客户列表
     * 
     * @param customerBO 我的客户
     * @return 我的客户集合
     */
    public List<Customer> selectCustomerList(CustomerBO customerBO);

    /**
     * 查询我的线索列表
     *
     * @param customerBO 我的客户
     * @return 我的线索集合
     */
    public List<Customer> selectXiansuoList(CustomerBO customerBO);

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

    public int updateGenjinDate(Integer id);


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

    /**
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    String importCustomer(List<Customer> userList, Boolean isUpdateSupport, String operName);

    /**
     * 定时任务：30天未跟进的客户转为线索
     */
    void degradeToXiansuo(int intervalDays);
}
