package com.zky.health.controller;

import com.zky.health.dao.UserMapper;
import com.zky.health.controller.entity.Result;
import com.zky.health.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：rc
 * @date ：Created in 2022/6/23 19:04
 * @description：
 */
@RestController
public class TestController {

    @Resource
    UserMapper userMapper;

    @RequestMapping(value = "/getUser")
    public Result  getUser(){
        Result result = new Result(true,"查询成功");
        result.setMessage("查询成功");
        return result;
    }

}
