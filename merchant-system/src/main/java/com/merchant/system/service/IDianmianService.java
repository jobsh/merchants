package com.merchant.system.service;

import java.util.List;
import com.merchant.system.domain.Dianmian;

/**
 * 店面管理Service接口
 * 
 * @author hanke
 * @date 2020-11-02
 */
public interface IDianmianService 
{
    /**
     * 查询店面管理
     * 
     * @param id 店面管理ID
     * @return 店面管理
     */
    public Dianmian selectDianmianById(Integer id);

    /**
     * 查询店面管理列表
     * 
     * @param dianmian 店面管理
     * @return 店面管理集合
     */
    public List<Dianmian> selectDianmianList(Dianmian dianmian);

    /**
     * 新增店面管理
     * 
     * @param dianmian 店面管理
     * @return 结果
     */
    public int insertDianmian(Dianmian dianmian);

    /**
     * 修改店面管理
     * 
     * @param dianmian 店面管理
     * @return 结果
     */
    public int updateDianmian(Dianmian dianmian);

    /**
     * 批量删除店面管理
     * 
     * @param ids 需要删除的店面管理ID
     * @return 结果
     */
    public int deleteDianmianByIds(Integer[] ids);

    /**
     * 删除店面管理信息
     * 
     * @param id 店面管理ID
     * @return 结果
     */
    public int deleteDianmianById(Integer id);
}
