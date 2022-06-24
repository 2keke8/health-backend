package com.zky.health.service;


import com.zky.health.entity.Reply;
import com.zky.health.entity.Topic;

import java.util.ArrayList;
import java.util.HashMap;

public interface TopicService {



    ArrayList<HashMap> getDetailsReplyByUserId(Integer userId, Integer questionId);

    HashMap<String, Object> getTopic(int parseInt, int parseInt1);

    boolean addTopics(ArrayList<Reply> replyList);

    boolean deleteTopics(int parseInt, int parseInt1);
}
