package com.zky.health.service.impl;

import com.zky.health.dao.*;
import com.zky.health.entity.Advice;
import com.zky.health.entity.Member;
import com.zky.health.entity.Reply;
import com.zky.health.entity.Topic;
import com.zky.health.service.MemberService;
import com.zky.health.service.QuestionService;
import com.zky.health.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author ：rc
 * @date ：Created in 2022/6/24 10:55
 * @description：
 */

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    ReplyMapper replyMapper;
    @Resource
    QuestionMapper questionMapper;
    @Resource
    TopicMapper topicMapper;
    @Resource
    MemberMapper memberMapper;

    @Override
    public ArrayList<HashMap> getReplyListByType(Integer question_id) {
//        通过问卷id查询 第一个问题的id
        Topic topic = topicMapper.selectOneByQuestionId(question_id);
//      拿到一个问题的id 用它去查reply表
        ArrayList<Reply> replies = replyMapper.selectByTopicId(topic.getTopicId());
        ArrayList<HashMap> resList = new ArrayList<>();
        for( Reply reply : replies){
            //存放指定数据
            HashMap<String,Object> tempMap = new HashMap();
            tempMap.put("user_id",reply.getUserId());
           Member member =  memberMapper.selectByPrimaryKey(reply.getUserId());
            tempMap.put("username",memberMapper.selectByPrimaryKey(reply.getUserId()).getName());
            tempMap.put("date",new SimpleDateFormat("yyyy-MM-dd").format(reply.getDate()));
            if(question_id == 1 ){
                for(int i=1;i<5;i++){
                    if(new Random().nextInt(2) == 1 ){
                        tempMap.put("tizhi"+String.valueOf(i),"是");
                    }else{
                        tempMap.put("tizhi"+String.valueOf(i),"否");
                    }

                }
            }
            if(question_id == 2){
                for(int i=1;i<5;i++){
                    if(new Random().nextInt(9) == 1 ){
                        tempMap.put("tizhi"+String.valueOf(i),"异常");
                    }else{
                        tempMap.put("tizhi"+String.valueOf(i),"正常");
                    }

                }
            }

            if(question_id == 3){
               tempMap.put("stu","已评估");
            }

//            tempMap.put("tizhi",tizhis);
            resList.add(tempMap);
        }
        return resList;
    }

    @Override
    public boolean getTestStu(int userID, int questionID) {
//        查询记录表中是否存在记录
//        先通过question id查一个topic
        Topic topic = topicMapper.selectOneByQuestionId(questionID);
//        查询记录
        Reply reply = replyMapper.selectByTopicIdAndUserID(topic.getTopicId(), userID);
        if(ObjectUtils.isEmpty(reply)){
            return  true;
        }else{
            return false;
        }
    }


}
