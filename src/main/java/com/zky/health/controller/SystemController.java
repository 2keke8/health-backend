package com.zky.health.controller;

import com.zky.health.entity.Result;
import com.zky.health.entity.Topic;
import com.zky.health.service.TopicService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ：rc
 * @date ：Created in 2022/6/24 10:15
 * @description：系统设置
 */

@RestController
@RequestMapping(value = "/api/system/")
public class SystemController {

    @Resource
    TopicService topicService;
    /*
    * 题库管理
    * */

    /*
     * 查询所有的问题做展示
     * */
    @RequestMapping(value = "queryalltopic")
    public Result QueryAllTopic(){
        Result result;
        ArrayList<Topic> topics = topicService.getTopicList();
        if(ObjectUtils.isEmpty(topics)){
            result = Result.error();
        }else{
            result = Result.success();
            HashMap<String,Object> map = new HashMap<>();
            map.put("topicList",topics);
            map.put("num",topics.size());
            result.setData(map);
        }

        return result;
    }

    /*
    *增加问题
    * */
    @RequestMapping(value = "addtopic")
    public Result addTopic(@RequestBody Topic topic){
        Result result;
        boolean OK = topicService.addTopic(topic);
        if(OK){
            result = Result.success();
        }else{
            result = Result.error();
        }
        return result;
    }


    /*
    * 删除问题
    * */

    @RequestMapping(value = "deletetopic")
    public Result deleteTopic(String topic_id){
        Result result;
        boolean OK = topicService.deleteTopic(Integer.parseInt(topic_id));
        if(OK){
            result = Result.success();
        }else{
            result = Result.error();
        }
        return result;
    }

    /*
    * 修改问题
    * */
}
