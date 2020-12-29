package com.merchant.system.service.impl;

import java.util.List;

import com.merchant.common.annotation.DataScope;
import com.merchant.common.enums.DianmianStatus;
import com.merchant.common.utils.DateUtils;
import com.merchant.common.utils.StringUtils;
import com.merchant.system.domain.DianmianLog;
import com.merchant.system.domain.bo.AddDianmianBO;
import com.merchant.system.domain.bo.DianmianBO;
import com.merchant.system.domain.vo.DianmianVO;
import com.merchant.system.mapper.DianmianLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.DianmianMapper;
import com.merchant.system.domain.Dianmian;
import com.merchant.system.service.IDianmianService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private DianmianLogMapper dianmianLogMapper;
    /**
     * 查询店面管理
     * 
     * @param id 店面管理ID
     * @return 店面管理
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public DianmianVO selectDianmianById(Integer id)
    {
        return dianmianMapper.selectDianmianById(id);
    }

    /**
     * 查询店面管理列表
     * 
     * @param dianmianBO 店面管理
     * @return 店面管理
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<DianmianVO> selectDianmianList(DianmianBO dianmianBO)
    {
        return dianmianMapper.selectDianmianList(dianmianBO);
    }

    /**
     * 新增店面管理
     * 
     * @param dianmian 店面管理
     * @return 结果
     */
    @Override
    public int insertDianmian(AddDianmianBO dianmian)
    {
        return dianmianMapper.insertDianmian(dianmian);
    }

    /**
     * 修改店面管理，更改店面状态也调用此接口
     * 
     * @param dianmian 店面管理
     * @return 结果
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateDianmian(Dianmian dianmian)
    {
        // 查询出原来店面状态
        DianmianVO dianmianVO = dianmianMapper.selectDianmianById(dianmian.getId());
        String oldDianmianStatus = dianmianVO.getStatus();
        // 如果修改门店状态需要记录日志
        if (StringUtils.isNoneBlank(dianmian.getStatus()) && !dianmian.getStatus().equals(oldDianmianStatus)){
            DianmianLog dianmianLog = new DianmianLog();
            dianmianLog.setDianmianId(dianmian.getId());
            if (DianmianStatus.SET.getCode().equals(dianmian.getStatus())) {
                dianmianLog.setSetDate(dianmian.getSetDate());
                dianmianLog.setStatus(dianmian.getStatus());
                dianmianLog.setOper(DianmianStatus.SET.getInfo());
            } else if (DianmianStatus.OPEN.getCode().equals(dianmian.getStatus())) {
                dianmianLog.setOpenDate(dianmian.getOpenDate());
                dianmianLog.setStatus(dianmian.getStatus());
                dianmianLog.setOper(DianmianStatus.OPEN.getInfo());
            } else if (DianmianStatus.ClOSED.getCode().equals(dianmian.getStatus())) {
                dianmian.setCloseDate(dianmian.getCloseDate());
                dianmianLog.setStatus(dianmian.getStatus());
                dianmianLog.setOper(DianmianStatus.ClOSED.getInfo());
                dianmianLog.setDiscription(dianmian.getCloseReason());
            } else {
                dianmianLog.setRestDate(dianmian.getOpenDate());
                dianmian.setCloseDate(dianmian.getCloseDate());
                dianmianLog.setOper(DianmianStatus.REST.getInfo());
                dianmianLog.setDiscription(dianmian.getCloseReason());
            }
            dianmianLogMapper.insertDianmianLog(dianmianLog);
        }
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

    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<DianmianVO> selectDianmianByContractNum(String contractNum) {
        DianmianBO dianmianBO = new DianmianBO();
        dianmianBO.setContractNum(contractNum);
        return dianmianMapper.selectDianmianByContractNum(contractNum);
    }

    @Override
    public int updateDianmianByContractNum(Dianmian dianmian) {
        return dianmianMapper.updateDianmianByContractNum(dianmian);
    }
}
