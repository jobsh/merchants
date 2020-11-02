package com.merchant.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.DianmianMapper;
import com.merchant.system.domain.Dianmian;
import com.merchant.system.service.IDianmianService;

/**
 * 店面管理Service业务层处理
 * 
 * @author hanke
 * @date 2020-11-02
 */
@Service
public class DianmianServiceImpl implements IDianmianService 
{
    @Autowired
    private DianmianMapper dianmianMapper;

    /**
     * 查询店面管理
     * 
     * @param id 店面管理ID
     * @return 店面管理
     */
    @Override
    public Dianmian selectDianmianById(Integer id)
    {
        return dianmianMapper.selectDianmianById(id);
    }

    /**
     * 查询店面管理列表
     * 
     * @param dianmian 店面管理
     * @return 店面管理
     */
    @Override
    public List<Dianmian> selectDianmianList(Dianmian dianmian)
    {
        return dianmianMapper.selectDianmianList(dianmian);
    }

    /**
     * 新增店面管理
     * 
     * @param dianmian 店面管理
     * @return 结果
     */
    @Override
    public int insertDianmian(Dianmian dianmian)
    {
        return dianmianMapper.insertDianmian(dianmian);
    }

    /**
     * 修改店面管理
     * 
     * @param dianmian 店面管理
     * @return 结果
     */
    @Override
    public int updateDianmian(Dianmian dianmian)
    {
        return dianmianMapper.updateDianmian(dianmian);
    }

    /**
     * 批量删除店面管理
     * 
     * @param ids 需要删除的店面管理ID
     * @return 结果
     */
    @Override
    public int deleteDianmianByIds(Integer[] ids)
    {
        return dianmianMapper.deleteDianmianByIds(ids);
    }

    /**
     * 删除店面管理信息
     * 
     * @param id 店面管理ID
     * @return 结果
     */
    @Override
    public int deleteDianmianById(Integer id)
    {
        return dianmianMapper.deleteDianmianById(id);
    }
}