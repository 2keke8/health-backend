package com.zky.health.dao;

import com.zky.health.entity.Reply;

import java.util.ArrayList;

public interface ReplyMapper {
    int deleteByPrimaryKey(Integer replyId);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer replyId);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);

    ArrayList<Reply> selectByTopicId(Integer topicId);

    Reply selectByTopicIdAndUserID(Integer topicId, Integer userId);
}