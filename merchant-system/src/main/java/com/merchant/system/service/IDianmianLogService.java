package com.merchant.system.service;

import java.util.List;
import com.merchant.system.domain.DianmianLog;

/**
 * 店面日志Service接口
 * 
 * @author hanke
 * @date 2020-11-26
 */
public interface IDianmianLogService 
{
    /**
     * 查询店面日志
     * 
     * @param id 店面日志ID
     * @return 店面日志
     */
    public DianmianLog selectDianmianLogById(Integer id);

    /**
     * 查询店面日志列表
     * 
     * @param dianmianLog 店面日志
     * @return 店面日志集合
     */
    public List<DianmianLog> selectDianmianLogList(DianmianLog dianmianLog);

    /**
     * 新增店面日志
     * 
     * @param dianmianLog 店面日志
     * @return 结果
     */
    public int insertDianmianLog(DianmianLog dianmianLog);

    /**
     * 修改店面日志
     * 
     * @param dianmianLog 店面日志
     * @return 结果
     */
    public int updateDianmianLog(DianmianLog dianmianLog);

    /**
     * 批量删除店面日志
     * 
     * @param ids 需要删除的店面日志ID
     * @return 结果
     */
    public int deleteDianmianLogByIds(Integer[] ids);

    /**
     * 删除店面日志信息
     * 
     * @param id 店面日志ID
     * @return 结果
     */
    public int deleteDianmianLogById(Integer id);
}
