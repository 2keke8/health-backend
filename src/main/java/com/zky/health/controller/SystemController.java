package com.zky.health.controller;

import com.zky.health.entity.Result;
import com.zky.health.entity.Topic;
import com.zky.health.entity.User;
import com.zky.health.service.TopicService;
import com.zky.health.service.UserService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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
    UserService userService;
    @Resource
    TopicService topicService;
    /*
    * 题库管理
    * */

    /*
     * 查询所有的问题做展示
     * */
    @GetMapping(value = "queryalltopic")
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
    @PostMapping(value = "addtopic")
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

    @PostMapping(value = "deletetopic")
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
    * 用户管理
    * */

    /*
    * 添加/修改用户
    * */
    @PostMapping(value = "user/{symbol}")
    public Result addUser(@RequestBody User user,
                          @PathVariable(value = "symbol") String symbol){
        Result result ;
        if("add".equals(symbol) || "update".equals(symbol)){
            boolean OK = userService.updateUser(user,symbol);
            if(OK){
                result = Result.success();
                result.setMessage("更新用户信息成功");
            }else{
                result = Result.error();
                result.setMessage("发生错误!更新用户失败!");
            }
        }else{
            result = Result.error();
            result.setMessage("请求路径错误!");
        }

        return result;
    }
    /*
    * 查询用户
    * */
    @GetMapping(value = "user/")
    public Result queryUser(){
        Result result ;
        //查询用户列表
        HashMap<String, Object> map = userService.selectAll();
        if(ObjectUtils.isEmpty(map)){
            result = Result.error();
            result.setMessage("查询用户信息失败！");
        }else{
            result = Result.success();
            result.setData(map);
        }
        return result;
    }

    /*
    * 删除用户
    * */
    @DeleteMapping(value = "user/{id}")
    public Result deleteUser(@PathVariable(value = "id") Integer id){
        Result result ;
        boolean OK = userService.deleteUser( id);
        if(OK){
            result = Result.success();
            result.setMessage("删除成功");
        }else{
            result = Result.error();
            result.setMessage("删除失败");
        }

        return result;
    }


}
