package com.zky.health.dao;

import com.zky.health.entity.Topic;

import java.util.ArrayList;

public interface TopicMapper {
    int deleteByPrimaryKey(Integer topicId);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer topicId);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);

    Topic selectOneByQuestionId(Integer question_id);

    ArrayList<Topic> selectByQuestionId(Integer questionId);
}