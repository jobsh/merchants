package com.merchant.system.mapper;

import java.util.List;
import com.merchant.system.domain.Genjin;
import com.merchant.system.domain.bo.GenjinBO;

/**
 * 客户跟进Mapper接口
 * 
 * @author hanke
 * @date 2020-10-29
 */
public interface GenjinMapper 
{
    /**
     * 查询客户跟进
     * 
     * @param id 客户跟进ID
     * @return 客户跟进
     */
    public Genjin selectGenjinById(Integer id);

    /**
     * 查询客户跟进列表
     * 
     * @param customerId 客户跟进
     * @return 客户跟进集合
     */
    public List<Genjin> selectGenjinList(Integer customerId);

    /**
     * 新增客户跟进
     * 
     * @param genjinBO 客户跟进
     * @return 结果
     */
    public int insertGenjin(GenjinBO genjinBO);

    /**
     * 修改客户跟进
     * 
     * @param genjin 客户跟进
     * @return 结果
     */
    public int updateGenjin(Genjin genjin);

    /**
     * 删除客户跟进
     * 
     * @param id 客户跟进ID
     * @return 结果
     */
    public int deleteGenjinById(Integer id);

    /**
     * 批量删除客户跟进
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGenjinByIds(Integer[] ids);
}
