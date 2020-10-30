package com.merchant.system.mapper;

import java.util.List;
import com.merchant.system.domain.Customer;
import com.merchant.system.domain.bo.CustomerBO;

/**
 * 我的客户Mapper接口
 * 
 * @author hanke
 * @date 2020-10-28
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
     * 查询我的线索
     *
     * @param id 我的客户ID
     * @return 我的客户
     */
    public Customer selectXiansuoById(Integer id);

    /**
     * 查询我的客户
     *
     * @param customerBO 我的客户ID
     * @return 我的客户
     */
    public List<Customer> selectCustomerByIds(CustomerBO customerBO);

    /**
     * 查询我的线索
     *
     * @param customerBO 我的客户ID
     * @return 我的客户
     */
    public List<Customer> selectXiansuoByIds(CustomerBO customerBO);

    /**
     * 查询我的客户列表
     * 
     * @param customerBO 我的客户
     * @return 我的客户集合
     */
    public List<Customer> selectCustomerList(CustomerBO customerBO);

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
     * 批量修改客户信息
     * @param customerBO
     * @return
     */
    int updateCustomerByIds(CustomerBO customerBO);

    /**
     * 超过intervalDay天数自动转为线索
     * @param intervalDay ： 未跟进天数
     * @return
     */
    int autoDegradeToXianSuo(Integer intervalDay);

    /**
     * 更新客户跟进最新时间
     * @param id 客户id
     * @return
     */
    int updateGenjinDate(Integer id);

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


    /**
     * 去除指定手机号相同的线索
     * @param phone
     */
    void clearRedundantXiansuo(String phone);

    /**
     * 根据手机号判断客户是否存在
     * @param phone
     * @return
     */
    int countCustomerByPhone(String phone);
}
