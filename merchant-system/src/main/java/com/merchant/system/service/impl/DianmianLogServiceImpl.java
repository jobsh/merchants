package com.merchant.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.DianmianLogMapper;
import com.merchant.system.domain.DianmianLog;
import com.merchant.system.service.IDianmianLogService;

/**
 * 店面日志Service业务层处理
 * 
 * @author hanke
 * @date 2020-11-26
 */
@Service
public class DianmianLogServiceImpl implements IDianmianLogService 
{
    @Autowired
    private DianmianLogMapper dianmianLogMapper;

    /**
     * 查询店面日志
     * 
     * @param id 店面日志ID
     * @return 店面日志
     */
    @Override
    public List<DianmianLog> selectDianmianLogById(Integer id)
    {
        return dianmianLogMapper.selectDianmianLogById(id);
    }

    /**
     * 查询店面日志列表
     * 
     * @param dianmianLog 店面日志
     * @return 店面日志
     */
    @Override
    public List<DianmianLog> selectDianmianLogList(DianmianLog dianmianLog)
    {
        return dianmianLogMapper.selectDianmianLogList(dianmianLog);
    }

    /**
     * 新增店面日志
     * 
     * @param dianmianLog 店面日志
     * @return 结果
     */
    @Override
    public int insertDianmianLog(DianmianLog dianmianLog)
    {
        return dianmianLogMapper.insertDianmianLog(dianmianLog);
    }

    /**
     * 修改店面日志
     * 
     * @param dianmianLog 店面日志
     * @return 结果
     */
    @Override
    public int updateDianmianLog(DianmianLog dianmianLog)
    {
        return dianmianLogMapper.updateDianmianLog(dianmianLog);
    }

    /**
     * 批量删除店面日志
     * 
     * @param ids 需要删除的店面日志ID
     * @return 结果
     */
    @Override
    public int deleteDianmianLogByIds(Integer[] ids)
    {
        return dianmianLogMapper.deleteDianmianLogByIds(ids);
    }

    /**
     * 删除店面日志信息
     * 
     * @param id 店面日志ID
     * @return 结果
     */
    @Override
    public int deleteDianmianLogById(Integer id)
    {
        return dianmianLogMapper.deleteDianmianLogById(id);
    }
}
