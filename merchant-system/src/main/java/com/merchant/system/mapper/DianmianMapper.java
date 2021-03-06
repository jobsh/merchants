package com.merchant.system.mapper;

import java.util.List;
import com.merchant.system.domain.Dianmian;
import com.merchant.system.domain.bo.AddDianmianBO;
import com.merchant.system.domain.bo.DianmianBO;
import com.merchant.system.domain.vo.DianmianVO;

/**
 * 店面管理Mapper接口
 * 
 * @author hanke
 * @date 2020-11-02
 */
public interface DianmianMapper 
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
     * 查询店面地图列表
     *
     * @param dianmianBO 店面管理
     * @return 店面管理集合
     */
    public List<DianmianVO> selectDianmianListForMap(DianmianBO dianmianBO);

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
     * 通过合同编号修改店面
     *
     * @param dianmian 店面管理
     * @return 结果
     */
    public int updateDianmianByContractNum(Dianmian dianmian);

    /**
     * 删除店面管理
     * 
     * @param id 店面管理ID
     * @return 结果
     */
    public int deleteDianmianById(Integer id);

    /**
     * 批量删除店面管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDianmianByIds(Integer[] ids);

    /**
     * 根据店面ids查询店面List
     * @param ids
     * @return
     */
    public List<Dianmian> selectDianmianListByIds(Integer[] ids);

    List<DianmianVO> selectDianmianByContractNum(String contractNum);
}
