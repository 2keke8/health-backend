package com.zky.health.controller;

import com.zky.health.entity.Reply;
import com.zky.health.entity.Result;
import com.zky.health.entity.Topic;
import com.zky.health.service.QuestionService;
import com.zky.health.service.TopicService;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ：rc
 * @date ：Created in 2022/6/24 10:15
 * @description：健康测评
 */

@RestController
@RequestMapping("/api/question/")
@Api(tags = "问卷相关接口")//swagger 标注这是一个控制器类
public class QuestionController {

    @Resource
    QuestionService questionService;
    @Resource
    TopicService topicService;

    /*
    * 查询所有测评报告
    * */
    @GetMapping("querybodytests")
    public Result QueryBodyTests(Integer question_id){

        Result result;
        ArrayList<HashMap> replies = questionService.getReplyListByType(question_id);
        if(ObjectUtils.isEmpty(replies)){
            result = Result.error();
            result.setMessage("查询失败");
        }else{
            result = Result.success();
            result.setMessage("查询成功");
            result.setData(replies);
        }

        return  result;
    }
/*
*
* 查看详细报告
* */
    @GetMapping("queryreplydetails/{user_id}/{question_id}")
    public Result QueryDetails(@PathVariable(value = "user_id") String UserId,
                               @PathVariable(value = "question_id") String QuestionId){
        Result result;
        ArrayList<HashMap> replies = topicService.getDetailsReplyByUserId(Integer.valueOf(UserId),Integer.valueOf(QuestionId));
        if(ObjectUtils.isEmpty(replies)){
            result = Result.error();
        }else{
            result = Result.success();
            result.setData(replies);
        }

        return  result;
    }

    /*
    *
    * 查询是否可做测评（用户功能，做测评）
    * ###### 查询是否可做测评（用户功能，做测评）
    * */
    @GetMapping("queryquestionstu/{user_id}/{question_id}")
    public Result QueryTestOk(@PathVariable(value = "user_id")String userId,
                              @PathVariable(value = "question_id")String questionId){
        Result result;
        boolean OK = questionService.getTestStu(Integer.parseInt(userId),Integer.parseInt(questionId));
        if(OK){
            result = Result.success();
            result.setMessage("您可以做当前测评！快去做一个吧！");
        }else{
            result = Result.error();
            result.setMessage("您不能做当前测评！很抱歉！");
        }
        return  result;
    }

    /*
    * 查询测评内容(用户功能，做测评)
    * */
    @GetMapping("querytestbyid/{user_id}/{question_id}")
    public Result QueryTestContent(@PathVariable(value = "user_id")String userId,
                                   @PathVariable(value = "question_id")String questionId){
        HashMap<String,Object> topics = topicService.getTopic(Integer.parseInt(userId),Integer.parseInt(questionId));
        Result result;
        if(ObjectUtils.isEmpty(topics)){
            result = Result.error();
        }else{
            result = Result.success();
            result.setData(topics);
        }
        return  result;
    }
    /*
    * 新增测评(用户功能，做测评)
    * */
    @PostMapping("addquestion")
    public Result addQuestion(@RequestBody ArrayList<Reply> replyList){
        boolean OK = topicService.addTopics(replyList);
        Result result;
        if(OK){
            result = Result.success();
            result.setMessage("测评上传成功!");
        }else{
            result = Result.error();
            result.setMessage("测评上传失败，请重新上传试试。");
        }

        return  result;
    }

    /*
    * 删除测评报告
    * */
    @GetMapping("deletequestion/{user_id}/{question_id}")
    public Result deleteQuestion(@PathVariable(value = "user_id")String userId,
                              @PathVariable(value = "question_id")String  questionId){
        boolean OK = topicService.deleteTopics(Integer.parseInt(userId),Integer.parseInt(questionId));
        Result result;
        if(OK){
            result = Result.success();
            result.setMessage("测评删除成功!");
        }else{
            result = Result.error();
            result.setMessage("测评删除失败，请重新删除试试。");
        }

        return  result;
    }

}
