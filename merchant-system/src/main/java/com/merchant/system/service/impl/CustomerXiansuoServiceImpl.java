package com.merchant.system.service.impl;

import java.util.List;
import com.merchant.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.CustomerXiansuoMapper;
import com.merchant.system.domain.CustomerXiansuo;
import com.merchant.system.service.ICustomerXiansuoService;

/**
 * 客户线索Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-19
 */
@Service
public class CustomerXiansuoServiceImpl implements ICustomerXiansuoService 
{
    @Autowired
    private CustomerXiansuoMapper customerXiansuoMapper;

    /**
     * 查询客户线索
     * 
     * @param id 客户线索ID
     * @return 客户线索
     */
    @Override
    public CustomerXiansuo selectCustomerXiansuoById(Long id)
    {
        return customerXiansuoMapper.selectCustomerXiansuoById(id);
    }

    /**
     * 查询客户线索列表
     * 
     * @param customerXiansuo 客户线索
     * @return 客户线索
     */
    @Override
    public List<CustomerXiansuo> selectCustomerXiansuoList(CustomerXiansuo customerXiansuo)
    {
        return customerXiansuoMapper.selectCustomerXiansuoList(customerXiansuo);
    }

    /**
     * 新增客户线索
     * 
     * @param customerXiansuo 客户线索
     * @return 结果
     */
    @Override
    public int insertCustomerXiansuo(CustomerXiansuo customerXiansuo)
    {
        customerXiansuo.setCreateTime(DateUtils.getNowDate());
        return customerXiansuoMapper.insertCustomerXiansuo(customerXiansuo);
    }

    /**
     * 修改客户线索
     * 
     * @param customerXiansuo 客户线索
     * @return 结果
     */
    @Override
    public int updateCustomerXiansuo(CustomerXiansuo customerXiansuo)
    {
        customerXiansuo.setUpdateTime(DateUtils.getNowDate());
        return customerXiansuoMapper.updateCustomerXiansuo(customerXiansuo);
    }

    /**
     * 批量删除客户线索
     * 
     * @param ids 需要删除的客户线索ID
     * @return 结果
     */
    @Override
    public int deleteCustomerXiansuoByIds(Long[] ids)
    {
        return customerXiansuoMapper.deleteCustomerXiansuoByIds(ids);
    }

    /**
     * 删除客户线索信息
     * 
     * @param id 客户线索ID
     * @return 结果
     */
    @Override
    public int deleteCustomerXiansuoById(Long id)
    {
        return customerXiansuoMapper.deleteCustomerXiansuoById(id);
    }
}
