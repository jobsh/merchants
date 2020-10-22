package com.merchant.system.mapper;

import java.util.List;
import com.merchant.system.domain.CustomerXiansuo;

/**
 * 客户线索Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-19
 */
public interface CustomerXiansuoMapper 
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
     * 删除客户线索
     * 
     * @param id 客户线索ID
     * @return 结果
     */
    public int deleteCustomerXiansuoById(Long id);

    /**
     * 批量删除客户线索
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustomerXiansuoByIds(Long[] ids);
}
