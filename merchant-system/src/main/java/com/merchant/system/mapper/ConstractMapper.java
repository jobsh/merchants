package com.merchant.system.mapper;

import java.util.List;
import com.merchant.system.domain.Constract;

/**
 * 合同管理Mapper接口
 * 
 * @author hanke
 * @date 2020-11-02
 */
public interface ConstractMapper 
{
    /**
     * 查询合同管理
     * 
     * @param id 合同管理ID
     * @return 合同管理
     */
    public Constract selectConstractById(Integer id);

    /**
     * 查询合同管理列表
     * 
     * @param constract 合同管理
     * @return 合同管理集合
     */
    public List<Constract> selectConstractList(Constract constract);

    /**
     * 新增合同管理
     * 
     * @param constract 合同管理
     * @return 结果
     */
    public int insertConstract(Constract constract);

    /**
     * 修改合同管理
     * 
     * @param constract 合同管理
     * @return 结果
     */
    public int updateConstract(Constract constract);

    /**
     * 删除合同管理
     * 
     * @param id 合同管理ID
     * @return 结果
     */
    public int deleteConstractById(Integer id);

    /**
     * 批量删除合同管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConstractByIds(Integer[] ids);
}
