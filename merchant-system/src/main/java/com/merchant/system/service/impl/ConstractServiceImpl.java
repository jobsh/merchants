package com.merchant.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.ConstractMapper;
import com.merchant.system.domain.Constract;
import com.merchant.system.service.IConstractService;

/**
 * 合同管理Service业务层处理
 * 
 * @author hanke
 * @date 2020-11-02
 */
@Service
public class ConstractServiceImpl implements IConstractService 
{
    @Autowired
    private ConstractMapper constractMapper;

    /**
     * 查询合同管理
     * 
     * @param id 合同管理ID
     * @return 合同管理
     */
    @Override
    public Constract selectConstractById(Integer id)
    {
        return constractMapper.selectConstractById(id);
    }

    /**
     * 查询合同管理列表
     * 
     * @param constract 合同管理
     * @return 合同管理
     */
    @Override
    public List<Constract> selectConstractList(Constract constract)
    {
        return constractMapper.selectConstractList(constract);
    }

    /**
     * 新增合同管理
     * 
     * @param constract 合同管理
     * @return 结果
     */
    @Override
    public int insertConstract(Constract constract)
    {
        return constractMapper.insertConstract(constract);
    }

    /**
     * 修改合同管理
     * 
     * @param constract 合同管理
     * @return 结果
     */
    @Override
    public int updateConstract(Constract constract)
    {
        return constractMapper.updateConstract(constract);
    }

    /**
     * 批量删除合同管理
     * 
     * @param ids 需要删除的合同管理ID
     * @return 结果
     */
    @Override
    public int deleteConstractByIds(Integer[] ids)
    {
        return constractMapper.deleteConstractByIds(ids);
    }

    /**
     * 删除合同管理信息
     * 
     * @param id 合同管理ID
     * @return 结果
     */
    @Override
    public int deleteConstractById(Integer id)
    {
        return constractMapper.deleteConstractById(id);
    }
}
