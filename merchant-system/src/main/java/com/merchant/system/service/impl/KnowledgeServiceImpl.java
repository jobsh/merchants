package com.merchant.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.merchant.system.mapper.KnowledgeMapper;
import com.merchant.system.domain.Knowledge;
import com.merchant.system.service.IKnowledgeService;

/**
 * 知识库Service业务层处理
 * 
 * @author hanke
 * @date 2020-12-18
 */
@Service
public class KnowledgeServiceImpl implements IKnowledgeService 
{
    @Autowired
    private KnowledgeMapper knowledgeMapper;

    /**
     * 查询知识库
     * 
     * @param id 知识库ID
     * @return 知识库
     */
    @Override
    public Knowledge selectKnowledgeById(Integer id)
    {
        return knowledgeMapper.selectKnowledgeById(id);
    }

    /**
     * 查询知识库列表
     * 
     * @param knowledge 知识库
     * @return 知识库
     */
    @Override
    public List<Knowledge> selectKnowledgeList(Knowledge knowledge)
    {
        return knowledgeMapper.selectKnowledgeList(knowledge);
    }

    /**
     * 新增知识库
     * 
     * @param knowledge 知识库
     * @return 结果
     */
    @Override
    public int insertKnowledge(Knowledge knowledge)
    {
        return knowledgeMapper.insertKnowledge(knowledge);
    }

    /**
     * 修改知识库
     * 
     * @param knowledge 知识库
     * @return 结果
     */
    @Override
    public int updateKnowledge(Knowledge knowledge)
    {
        return knowledgeMapper.updateKnowledge(knowledge);
    }

    /**
     * 批量删除知识库
     * 
     * @param ids 需要删除的知识库ID
     * @return 结果
     */
    @Override
    public int deleteKnowledgeByIds(Integer[] ids)
    {
        return knowledgeMapper.deleteKnowledgeByIds(ids);
    }

    /**
     * 逻辑删除知识库信息
     * 
     * @param id 知识库ID
     * @return 结果
     */
    @Override
    public int logicDeleteKnowledgeById(Integer id) {

        return knowledgeMapper.logicDeleteKnowledgeById(id);
    }

    /**
     * 逻辑删除知识库信息
     *
     * @param id 知识库ID
     * @return 结果
     */
    @Override
    public int forbiddenKnowledgeById(String status,Integer id) {

        return knowledgeMapper.forbiddenKnowledgeById(status,id);
    }
}
