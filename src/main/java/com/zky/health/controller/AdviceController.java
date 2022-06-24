package com.zky.health.controller;

import com.zky.health.entity.Advice;
import com.zky.health.entity.Result;
import com.zky.health.service.AdviceService;
import com.zky.health.service.QuestionService;
import com.zky.health.service.TopicService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ：rc
 * @date ：Created in 2022/6/24 10:15
 * @description：健康干预，提供建议
 */
@RestController
@RequestMapping("/advice/")

public class AdviceController {



    @Resource
    TopicService topicService;
    @Resource
    AdviceService adviceService;
    /*
    * 查询所有方案
    * */
    @RequestMapping(value = "queryalladvice")
    public Result QueryAllAdvice(){
        Result result = null;
        ArrayList<Advice> adviceDetails = new ArrayList<>();
        HashMap<String,Object> mapper = new HashMap<>();

        adviceDetails = adviceService.getAlladvice();
        if(ObjectUtils.isEmpty(adviceDetails)){
             result =Result.error();
        }else{
             result = Result.success();
             result.setData(adviceDetails);

        }
        return  result;
    }
    /*
    * 查询方案内容
    * */
    @RequestMapping(value = "queryadvicedetails/{user_id}")
    public Result QueryAdviceDetails(){

        return null;
    }
    /*
    * 新增方案
    * */
    @RequestMapping(value = "addadvice")
    public Result addAdvice(Integer user_id,Integer healther_id,String content){

        return null;
    }
    /*
    * 删除方案
    * */
    @RequestMapping(value = "deleteadvice/{advice_id}")
    public Result deleteAdvice(@PathVariable(value = "advice_id") Integer advice_id){

        return null;
    }

}
