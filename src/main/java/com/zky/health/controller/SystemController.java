package com.zky.health.controller;

import com.zky.health.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：rc
 * @date ：Created in 2022/6/24 10:15
 * @description：系统设置
 */

@RestController
@RequestMapping(value = "/system/")
public class SystemController {

    /*
    * 题库管理
    * */

    /*
    * 查询所有的问题做展示
    * */
    @RequestMapping(value = "queryalltopic")
    public Result QueryAllTopic(){

        return null;
    }

    /*
    *增加问题
    * */
    @RequestMapping(value = "addtopic")
    public Result addTopic(String name,Integer question_id){

        return null;
    }


    /*
    * 删除问题
    * */

    @RequestMapping(value = "deletetopic")
    public Result deleteTopic(Integer topic_id){

        return null;
    }
}
