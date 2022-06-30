package com.zky.health.service.impl;

import com.zky.health.dao.QuestionMapper;
import com.zky.health.dao.ReplyMapper;
import com.zky.health.dao.TopicMapper;
import com.zky.health.entity.Reply;
import com.zky.health.entity.Topic;
import com.zky.health.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ：rc
 * @date ：Created in 2022/6/24 10:50
 * @description：
 */
@Service
public class TopicServiceImpl implements TopicService {

    @Resource
    TopicMapper topicMapper;
    @Resource
    QuestionMapper questionMapper;
    @Resource
    ReplyMapper replyMapper;

    @Override
    public ArrayList<HashMap> getDetailsReplyByUserId(Integer userId, Integer questionId) {

//        查question_id中的所有的topic_id
        ArrayList<Topic> topics = topicMapper.selectByQuestionId(questionId);
        ArrayList<HashMap> res = new ArrayList<>();


        if(ObjectUtils.isEmpty(topics)){
            res = null;
        }else{

            for(Topic topic:topics){
                HashMap<String,Object> map = new HashMap<>();

                //查找问题名
                String name = topicMapper.selectByPrimaryKey(topic.getTopicId()).getName();
                map.put("name",name);
                //        从reply按照user_id topic_id来查找
                map.put("details",replyMapper.selectByTopicIdAndUserID(topic.getTopicId(),userId));
                res.add(map);
            }
        }
        return res;
    }

    @Override
    public HashMap<String, Object> getTopic(int userID, int questionID) {
        /*
        * userID没用 冗余了
        * */
        HashMap<String,Object> map = new HashMap<>();
//        查出questionID的所有topic
        ArrayList<Topic> topics = topicMapper.selectByQuestionId(questionID);
        if(!ObjectUtils.isEmpty(topics)){
            map.put("topics",topics);
            map.put("num",topics.size());
        }
        return map;
    }

    @Override
//    Replies
    public boolean addTopics(ArrayList<Reply> replyList) {
        Integer num = replyList.size();
        Integer res = 0;
        for(Reply reply : replyList){
            res += replyMapper.insert(reply);
        }
        return num==res;
    }

    @Override
    public boolean deleteTopics(int userID, int questionID) {
//        查quesitonID的所有topic
        ArrayList<Topic> topics = topicMapper.selectByQuestionId(questionID);
        Integer num = topics.size();
        Integer res = 0;
        for(Topic topic : topics){
//             通过topic和UserID查出一条记录
            Reply reply = replyMapper.selectByTopicIdAndUserID(topic.getTopicId(), userID);
//删除
            if(!ObjectUtils.isEmpty(reply)){
                res+=replyMapper.deleteByPrimaryKey(reply.getReplyId());
            }

        }
        return res>0;
    }

    @Override
    public ArrayList<Topic> getTopicList() {

        ArrayList<Topic> topics =  topicMapper.selectAll();
        return topics;
    }

    @Override
    public boolean addTopic(Topic topic) {
        Integer num = topicMapper.insertSelective(topic);
        return num==1;
    }

    @Override
    public boolean deleteTopic(int topic_id) {
        Integer num = topicMapper.deleteByPrimaryKey(topic_id);
        return num==1;
    }


}
