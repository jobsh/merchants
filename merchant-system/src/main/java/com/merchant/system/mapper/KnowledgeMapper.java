package com.merchant.system.mapper;

import java.util.List;
import com.merchant.system.domain.Knowledge;
import org.apache.ibatis.annotations.Param;

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
     * 批量逻辑删除知识库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKnowledgeByIds(Integer[] ids);

    int logicDeleteKnowledgeById(@Param("id") Integer id);

    int forbiddenKnowledgeById(@Param("status") String status, @Param("id") Integer id);
}
