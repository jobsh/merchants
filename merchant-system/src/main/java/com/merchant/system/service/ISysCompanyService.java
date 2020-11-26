package com.merchant.system.service;

import java.util.List;
import com.merchant.common.core.domain.entity.SysCompany;

/**
 * 公司Service接口
 * 
 * @author hanke
 * @date 2020-11-25
 */
public interface ISysCompanyService 
{
    /**
     * 查询公司
     * 
     * @param id 公司ID
     * @return 公司
     */
    public SysCompany selectSysCompanyById(Integer id);

    /**
     * 查询公司列表
     * 
     * @param sysCompany 公司
     * @return 公司集合
     */
    public List<SysCompany> selectSysCompanyList(SysCompany sysCompany);

    /**
     * 新增公司
     * 
     * @param sysCompany 公司
     * @return 结果
     */
    public int insertSysCompany(SysCompany sysCompany);

    /**
     * 修改公司
     * 
     * @param sysCompany 公司
     * @return 结果
     */
    public int updateSysCompany(SysCompany sysCompany);

    /**
     * 批量删除公司
     * 
     * @param ids 需要删除的公司ID
     * @return 结果
     */
    public int deleteSysCompanyByIds(Integer[] ids);

    /**
     * 删除公司信息
     * 
     * @param id 公司ID
     * @return 结果
     */
    public int deleteSysCompanyById(Integer id);
}
