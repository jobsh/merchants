package com.merchant.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.SysCompanyMapper;
import com.merchant.common.core.domain.entity.SysCompany;
import com.merchant.system.service.ISysCompanyService;

/**
 * 公司Service业务层处理
 * 
 * @author hanke
 * @date 2020-11-25
 */
@Service
public class SysCompanyServiceImpl implements ISysCompanyService 
{
    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    /**
     * 查询公司
     * 
     * @param id 公司ID
     * @return 公司
     */
    @Override
    public SysCompany selectSysCompanyById(Integer id)
    {
        return sysCompanyMapper.selectSysCompanyById(id);
    }

    /**
     * 查询公司列表
     * 
     * @param sysCompany 公司
     * @return 公司
     */
    @Override
    public List<SysCompany> selectSysCompanyList(SysCompany sysCompany)
    {
        return sysCompanyMapper.selectSysCompanyList(sysCompany);
    }

    /**
     * 新增公司
     * 
     * @param sysCompany 公司
     * @return 结果
     */
    @Override
    public int insertSysCompany(SysCompany sysCompany)
    {
        return sysCompanyMapper.insertSysCompany(sysCompany);
    }

    /**
     * 修改公司
     * 
     * @param sysCompany 公司
     * @return 结果
     */
    @Override
    public int updateSysCompany(SysCompany sysCompany)
    {
        return sysCompanyMapper.updateSysCompany(sysCompany);
    }

    /**
     * 批量删除公司
     * 
     * @param ids 需要删除的公司ID
     * @return 结果
     */
    @Override
    public int deleteSysCompanyByIds(Integer[] ids)
    {
        return sysCompanyMapper.deleteSysCompanyByIds(ids);
    }

    /**
     * 删除公司信息
     * 
     * @param id 公司ID
     * @return 结果
     */
    @Override
    public int deleteSysCompanyById(Integer id)
    {
        return sysCompanyMapper.deleteSysCompanyById(id);
    }
}
