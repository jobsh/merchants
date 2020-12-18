package com.merchant.system.service;

import java.util.List;
import com.merchant.system.domain.Dianmian;
import com.merchant.system.domain.bo.AddDianmianBO;
import com.merchant.system.domain.bo.DianmianBO;
import com.merchant.system.domain.vo.DianmianVO;

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
    public DianmianVO selectDianmianById(Integer id);

    /**
     * 查询店面管理列表
     * 
     * @param dianmianBO 店面管理
     * @return 店面管理集合
     */
    public List<DianmianVO> selectDianmianList(DianmianBO dianmianBO);

    /**
     * 新增店面管理
     * 
     * @param dianmian 店面管理
     * @return 结果
     */
    public int insertDianmian(AddDianmianBO dianmian);

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

    List<DianmianVO> selectDianmianByContractNum(String contractNum);

    /**
     * 跟进合同修改店面
     * @param dianmian
     */
    int updateDianmianByContractNum(Dianmian dianmian);
}
