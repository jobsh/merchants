package com.merchant.system.mapper;

import java.util.List;
import com.merchant.system.domain.Knowledge;

/**
 * 知识库Mapper接口
 * 
 * @author hanke
 * @date 2020-12-18
 */
public interface KnowledgeMapper 
{
    /**
     * 查询知识库
     * 
     * @param id 知识库ID
     * @return 知识库
     */
    public Knowledge selectKnowledgeById(Long id);

    /**
     * 查询知识库列表
     * 
     * @param knowledge 知识库
     * @return 知识库集合
     */
    public List<Knowledge> selectKnowledgeList(Knowledge knowledge);

    /**
     * 新增知识库
     * 
     * @param knowledge 知识库
     * @return 结果
     */
    public int insertKnowledge(Knowledge knowledge);

    /**
     * 修改知识库
     * 
     * @param knowledge 知识库
     * @return 结果
     */
    public int updateKnowledge(Knowledge knowledge);

    /**
     * 删除知识库
     * 
     * @param id 知识库ID
     * @return 结果
     */
    public int deleteKnowledgeById(Long id);

    /**
     * 批量删除知识库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKnowledgeByIds(Long[] ids);
}
