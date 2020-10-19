package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CustomerXiansuo;

/**
 * 客户线索Service接口
 * 
 * @author ruoyi
 * @date 2020-10-19
 */
public interface ICustomerXiansuoService 
{
    /**
     * 查询客户线索
     * 
     * @param id 客户线索ID
     * @return 客户线索
     */
    public CustomerXiansuo selectCustomerXiansuoById(Long id);

    /**
     * 查询客户线索列表
     * 
     * @param customerXiansuo 客户线索
     * @return 客户线索集合
     */
    public List<CustomerXiansuo> selectCustomerXiansuoList(CustomerXiansuo customerXiansuo);

    /**
     * 新增客户线索
     * 
     * @param customerXiansuo 客户线索
     * @return 结果
     */
    public int insertCustomerXiansuo(CustomerXiansuo customerXiansuo);

    /**
     * 修改客户线索
     * 
     * @param customerXiansuo 客户线索
     * @return 结果
     */
    public int updateCustomerXiansuo(CustomerXiansuo customerXiansuo);

    /**
     * 批量删除客户线索
     * 
     * @param ids 需要删除的客户线索ID
     * @return 结果
     */
    public int deleteCustomerXiansuoByIds(Long[] ids);

    /**
     * 删除客户线索信息
     * 
     * @param id 客户线索ID
     * @return 结果
     */
    public int deleteCustomerXiansuoById(Long id);
}
