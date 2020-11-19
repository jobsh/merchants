package com.merchant.system.service;

import java.util.List;
import com.merchant.system.domain.ContractFile;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author hanke
 * @date 2020-11-18
 */
public interface IContractFileService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public ContractFile selectContractFileById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param contractFile 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ContractFile> selectContractFileList(ContractFile contractFile);

    /**
     * 新增【请填写功能名称】
     * 
     * @param contractFile 【请填写功能名称】
     * @return 结果
     */
    public int insertContractFile(ContractFile contractFile);

    /**
     * 修改【请填写功能名称】
     * 
     * @param contractFile 【请填写功能名称】
     * @return 结果
     */
    public int updateContractFile(ContractFile contractFile);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteContractFileByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteContractFileById(Long id);
}
