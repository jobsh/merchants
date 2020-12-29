package com.merchant.system.service;

import com.merchant.system.domain.Knowledge;

import java.util.List;

/**
 * 知识库Service接口
 * 
 * @author hanke
 * @date 2020-12-18
 */
public interface IKnowledgeService 
{
    /**
     * 查询知识库
     * 
     * @param id 知识库ID
     * @return 知识库
     */
    public Knowledge selectKnowledgeById(Integer id);

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
     * 批量删除知识库
     * 
     * @param ids 需要删除的知识库ID
     * @return 结果
     */
    public int deleteKnowledgeByIds(Integer[] ids);

    /**
     * 逻辑删除与否
     * @param id
     * @return
     */
    int logicDeleteKnowledgeById(Integer id);

    /**
     * 禁用
     * @param status
     * @param id
     * @return
     */
    public int forbiddenKnowledgeById(String status,Integer id);
}
