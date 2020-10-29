package com.merchant.system.service;

import java.io.IOException;
import java.util.List;
import com.merchant.system.domain.Genjin;
import com.merchant.system.domain.bo.GenjinBO;

/**
 * 客户跟进Service接口
 * 
 * @author hanke
 * @date 2020-10-29
 */
public interface IGenjinService 
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
     * @param genjin 客户跟进
     * @return 结果
     */
    public int insertGenjin(GenjinBO genjinBO) throws IOException;

    /**
     * 修改客户跟进
     * 
     * @param genjin 客户跟进
     * @return 结果
     */
    public int updateGenjin(Genjin genjin);

    /**
     * 批量删除客户跟进
     * 
     * @param ids 需要删除的客户跟进ID
     * @return 结果
     */
    public int deleteGenjinByIds(Integer[] ids);

    /**
     * 删除客户跟进信息
     * 
     * @param id 客户跟进ID
     * @return 结果
     */
    public int deleteGenjinById(Integer id);
}
