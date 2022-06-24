package com.zky.health.controller;

import com.zky.health.entity.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：rc
 * @date ：Created in 2022/6/24 10:15
 * @description：健康测评
 */

@RestController
@RequestMapping("/question/")
public class QuestionController {
    /*
    * 查询所有测评报告
    * */
    @RequestMapping("querybodytests")
    public Result QueryBodyTests(Integer question_id){


        Result result = new Result(true,"成功");
        return  result;
    }
/*
*
* 查看详细报告
* */
    @RequestMapping("queryreplydetails/{user_id}")
    public Result QueryDetails(@PathVariable(value = "user_id") Integer id){

        Result result = new Result(true,"成功");
        return  result;
    }

    /*
    *
    * 查询是否可做测评（用户功能，做测评）
    * */
    @RequestMapping("queryquestionstu/{user_id}/{question_id}")
    public Result QueryTestOk(@PathVariable(value = "user_id")Integer userId,
                              @PathVariable(value = "question_id")Integer questionId){


        Result result = new Result(true,"成功");
        return  result;
    }

    /*
    * 查询测评内容(用户功能，做测评)
    * */
    @RequestMapping("querytestbyid/{user_id}/{question_id}")
    public Result QueryTestContent(@PathVariable(value = "user_id")Integer userId,
                                   @PathVariable(value = "question_id")Integer questionId){

        Result result = new Result(true,"成功");
        return  result;
    }
    /*
    * 新增测评(用户功能，做测评)
    * */
    @RequestMapping("addquestion/{user_id}/{question_id}")
    public Result addQuestion(@PathVariable(value = "user_id")Integer userId,
                              @PathVariable(value = "question_id")Integer questionId){

        Result result = new Result(true,"成功");

        return  result;
    }

    /*
    * 删除测评报告
    * */
    @RequestMapping("deletequestion/{user_id}/{question_id}")
    public Result deleteQuestion(@PathVariable(value = "user_id")Integer userId,
                              @PathVariable(value = "question_id")Integer questionId){


        Result result = new Result(true,"成功");
        return  result;
    }

}
