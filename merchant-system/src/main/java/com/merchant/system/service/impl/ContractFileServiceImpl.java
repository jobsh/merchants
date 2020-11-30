package com.merchant.system.service.impl;

import java.io.IOException;
import java.util.List;

import com.merchant.common.config.MerchantConfig;
import com.merchant.common.utils.file.FileUploadUtils;
import com.merchant.system.domain.bo.ContractBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.ContractFileMapper;
import com.merchant.system.domain.ContractFile;
import com.merchant.system.service.IContractFileService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author hanke
 * @date 2020-11-18
 */
@Service
public class ContractFileServiceImpl implements IContractFileService 
{
    @Autowired
    private ContractFileMapper contractFileMapper;

    @Override
    public ContractFile selectContractFileById(Long id)
    {
        return contractFileMapper.selectContractFileById(id);
    }

    @Override
    public List<ContractFile> selectContractFileList(ContractFile contractFile)
    {
        return contractFileMapper.selectContractFileList(contractFile);
    }

    @Override
    public int insertContractFile(ContractFile contractFile)
    {
        return contractFileMapper.insertContractFile(contractFile);
    }

    @Override
    public int updateContractFile(ContractFile contractFile)
    {
        return contractFileMapper.updateContractFile(contractFile);
    }

    @Override
    public int deleteContractFileByIds(Long[] ids)
    {
        return contractFileMapper.deleteContractFileByIds(ids);
    }

    @Override
    public int deleteContractFileById(Long id)
    {
        return contractFileMapper.deleteContractFileById(id);
    }

    /**
     * 合同附件上传
     */
    public void addSave(MultipartFile file, ContractBO contractBO) throws IOException {
        // 上传文件路径
        String filePath = MerchantConfig.getContractPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        contractBO.setFile(fileName);
    }
}
