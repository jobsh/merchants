package com.merchant.system.service;

import java.io.IOException;
import java.util.List;
import com.merchant.system.domain.Contract;
import com.merchant.system.domain.bo.AddContractBO;
import com.merchant.system.domain.bo.ContractBO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 合同Service接口
 * 
 * @author hanke
 * @date 2020-11-03
 */
public interface IContractService 
{
    /**
     * 查询合同
     * 
     * @param id 合同ID
     * @return 合同
     */
    public Contract selectContractById(Integer id);

    /**
     * 根据rootId查询关联合同
     * @param rootNum
     * @return
     */
    List<Contract> selectContractByRootNum(String rootNum);

    /**
     * 查询合同列表
     * 
     * @param contractBO 合同
     * @return 合同集合
     */
    public List<Contract> selectContractList(ContractBO contractBO);

    /**
     * 新增合同
     * 
     * @param contractBO 合同
     * @return 结果
     */
    public int insertContract(AddContractBO contractBO);

    /**
     * 修改合同
     * 
     * @param contractBO 合同
     * @return 结果
     */
    public int updateContract(ContractBO contractBO) throws IllegalAccessException, NoSuchFieldException, Exception;

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的合同ID
     * @return 结果
     */
    public int deleteContractByIds(Integer[] ids);

    /**
     * 删除合同信息
     * 
     * @param id 合同ID
     * @return 结果
     */
    public int deleteContractById(Integer id);

    /**
     * 根据customerId查询合同list
     * @param customerId
     * @return
     */
    List<Contract> selectContractListByCustomerId(Integer customerId);

    /**
     * 根据合同id解约合同
     *
     * @param file
     * @param contractBO
     * @return
     */
    int terminate(ContractBO contractBO) throws IOException;

    /**
     * 续签合同
     * @param contractBO
     * @return
     */
    int renew(Integer id, AddContractBO contractBO);

    /**
     * 转移给手机号为phone的负责人
     * @return
     */
    int transfer(Integer[] ids, Integer id) throws IllegalAccessException;

    /**
     * 审核合同
     * @param id
     * @param signDate
     * @return
     */
    int check(Integer id, String signDate) throws IllegalAccessException;


    /**
     * 反审核合同
     * @param id
     * @return
     */
    int uncheck(Integer id);

    /**
     * 合同失效
     * @param id
     * @return
     */
    int abandon(Integer id);

    /**
     * 更改合同附件路径信息
     * @param id
     * @param files
     * @return
     */
    int uploadContractFile(Integer id, String files);

    /**
     * 更改合同图片路径信息
     * @param id
     * @param imgs
     * @return
     */
    int uploadContractImgs(Integer id, String imgs);

    Contract selectContractByNum(String num);

    /**
     * 合同自动到期
     * @param contractBO
     */
    void autoExpire(ContractBO contractBO);

    void autoBegin(ContractBO contractBO);
}
