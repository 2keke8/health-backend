package com.zky.health.controller;

import com.zky.health.dao.UserMapper;
import com.zky.health.entity.Advice;
import com.zky.health.entity.Result;
import com.zky.health.entity.User;
import com.zky.health.service.AdviceService;
import com.zky.health.service.QuestionService;
import com.zky.health.service.TopicService;
import com.zky.health.service.UserService;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ：rc
 * @date ：Created in 2022/6/24 10:15
 * @description：健康干预，提供建议
 */
@RestController
@RequestMapping("/api/advice/")
@Api (tags = "方案相关接口")//swagger 标注这是一个控制器类
public class AdviceController {

    @Autowired
    UserService userService;
    @Resource
    UserMapper userMapper;

    @Resource
    TopicService topicService;
    @Resource
    AdviceService adviceService;
    /*
    * 查询所有方案
    * */
    @GetMapping(value = "queryalladvice")
    public Result QueryAllAdvice(){
        Result result ;
        ArrayList<Advice> adviceDetails;
        ArrayList<HashMap> resList= new ArrayList<>();

        adviceDetails = adviceService.getAlladvice();
        if(ObjectUtils.isEmpty(adviceDetails)){
            result =Result.error();
        }else{
            for(Advice advice: adviceDetails){
                HashMap<String, Object> details = new HashMap<>();
                User user = userMapper.selectByPrimaryKey(advice.getUserId());
                String username = user.getUsername();
                String healtherName = userMapper.selectByPrimaryKey(advice.getHealtherId()).getUsername();
                details.put("advice",advice);
                details.put("user_name",username);
                details.put("healther_name",healtherName);
                resList.add(details);
            }
            result = Result.success();
            result.setData(resList);

        }


        return  result;
    }
    /*
    * 查询方案内容
    * */
    @GetMapping(value = "queryadvicedetails/{user_id}")
    public Result QueryAdviceDetails(@PathVariable(value = "user_id")Integer userID){
        Result result = null;

        String content = adviceService.getContentByUserid(userID);
        if(StringUtils.hasText(content)){
            result = Result.success();
            HashMap<String,Object> data = new HashMap<>();
            data.put("content",content);
            result.setData(data);
        }else{
            result = Result.error();
            result.setMessage("查询失败");
        }
        return result;
    }
    /*
    * 新增方案
    * */
    @PostMapping(value = "addadvice")
    public Result addAdvice(String user_id,String healther_id,String content){
        Result result = null;

        Advice advice = new Advice();
        advice.setUserId(Integer.parseInt(user_id));
        advice.setHealtherId(Integer.parseInt(healther_id));
        advice.setContent(content);
        boolean OK = adviceService.addAdvice(advice);
        if(OK){
            result = Result.success();
            result.setMessage("添加方案成功！");
        }else{
            result = Result.error();
            result.setMessage("添加方案失败！");
        }
        return result;
    }
    /*
    * 删除方案
    * */
    @PostMapping(value = "deleteadvice/{advice_id}")
    public Result deleteAdvice(@PathVariable(value = "advice_id") String advice_id){
        Result result = null;

        boolean OK = adviceService.deleteAdvice(Integer.parseInt(advice_id));

        if(OK){
            result = Result.success();
            result.setMessage("删除方案成功！");
        }else{
            result = Result.error();
            result.setMessage("删除方案失败！");
        }
        return result;
    }

}
