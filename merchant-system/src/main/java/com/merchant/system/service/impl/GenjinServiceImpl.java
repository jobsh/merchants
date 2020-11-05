package com.merchant.system.service.impl;

import java.io.IOException;
import java.util.List;

import com.merchant.common.config.MerchantConfig;
import com.merchant.common.utils.file.FileUploadUtils;
import com.merchant.system.domain.bo.GenjinBO;
import com.merchant.system.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.GenjinMapper;
import com.merchant.system.domain.Genjin;
import com.merchant.system.service.IGenjinService;

/**
 * 客户跟进Service业务层处理
 *
 * @author hanke
 * @date 2020-10-29
 */
@Service
public class GenjinServiceImpl implements IGenjinService {
    @Autowired
    private GenjinMapper genjinMapper;

    @Autowired
    private ICustomerService customerService;
    /**
     * 查询客户跟进
     *
     * @param id 客户跟进ID
     * @return 客户跟进
     */
    @Override
    public Genjin selectGenjinById(Integer id) {
        return genjinMapper.selectGenjinById(id);
    }

    /**
     * 查询客户跟进列表
     *
     * @param customerId 客户跟进
     * @return 客户跟进
     */
    @Override
    public List<Genjin> selectGenjinList(Integer customerId) {
        return genjinMapper.selectGenjinList(customerId);
    }

    /**
     * 新增客户跟进
     *
     * @param genjinBO 客户跟进
     * @return 结果
     */
    @Override
    public int insertGenjin(GenjinBO genjinBO) throws IOException {
        // 更新customer最新跟进时间
        customerService.updateGenjinDate(genjinBO.getCustomerId());
        return genjinMapper.insertGenjin(genjinBO);
    }

    /**
     * 修改客户跟进
     *
     * @param genjinBO 客户跟进
     * @return 结果
     */
    @Override
    public int updateGenjin(GenjinBO genjinBO) {
        return genjinMapper.updateGenjin(genjinBO);
    }

    /**
     * 批量删除客户跟进
     *
     * @param ids 需要删除的客户跟进ID
     * @return 结果
     */
    @Override
    public int deleteGenjinByIds(Integer[] ids) {
        return genjinMapper.deleteGenjinByIds(ids);
    }

    /**
     * 删除客户跟进信息
     *
     * @param id 客户跟进ID
     * @return 结果
     */
    @Override
    public int deleteGenjinById(Integer id) {
        return genjinMapper.deleteGenjinById(id);
    }

    @Override
    public boolean updateGenjinImage(String imgPath) {
        GenjinBO genjinBO = new GenjinBO();
        genjinBO.setImage(imgPath);
        return genjinMapper.updateGenjin(genjinBO) > 0;
    }
}
