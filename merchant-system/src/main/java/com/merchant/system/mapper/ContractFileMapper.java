package com.merchant.system.mapper;

import java.util.List;
import com.merchant.system.domain.ContractFile;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author hanke
 * @date 2020-11-18
 */
public interface ContractFileMapper 
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
     * 修改【请填写功能名称】
     * 
     * @param contractFile
     * @return
     */
    public int updateContractFile(ContractFile contractFile);

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    public int deleteContractFileById(Long id);

    /**
     *
     * 
     * @param ids
     * @return
     */
    public int deleteContractFileByIds(Long[] ids);
}
