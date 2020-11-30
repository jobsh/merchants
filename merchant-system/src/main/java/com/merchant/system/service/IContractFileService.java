package com.merchant.system.service;

import java.util.List;
import com.merchant.system.domain.ContractFile;

/**
 * 合同附件Service接口
 * 
 * @author hanke
 * @date 2020-11-18
 */
public interface IContractFileService 
{
    /**
     * 根据id查询合同附件信息
     *
     * @param id
     * @return  ContractFile
     */
    public ContractFile selectContractFileById(Long id);

    /**
     * 查询合同附件列表
     *
     * @param contractFile
     * @return
     */
    public List<ContractFile> selectContractFileList(ContractFile contractFile);

    /**
     * 新增
     *
     * @param contractFile
     * @return
     */
    public int insertContractFile(ContractFile contractFile);

    /**
     * 修改
     * 
     * @param contractFile
     * @return
     */
    public int updateContractFile(ContractFile contractFile);

    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    public int deleteContractFileByIds(Long[] ids);

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    public int deleteContractFileById(Long id);
}
