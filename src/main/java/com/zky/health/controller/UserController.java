package com.zky.health.controller;

import com.zky.health.entity.Result;
import com.zky.health.entity.User;
import com.zky.health.service.UserService;
import com.zky.health.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-23 17:48
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public Result login(String username, String password){

        if(!StringUtils.hasText(username) || !StringUtils.hasText(password)){
            return new Result(false,"用户名或密码不能为空哦~");
        }

        int res = userService.login(username, password);

        if(res == 0){
            return new Result(false,"用户名不存在，请联系管理员~");
        }
        //判断密码是否正确
        if(res == -1){
            return new Result(false,"密码错误，请输入正确密码！");
        }

        //封装返回结果
        HashMap<String, Object> data = new HashMap<>();

        User user = userService.selectUserByname(username);
        String token = userService.createToken(username);
        data.put("user",user);
        data.put("token",token);
        //登录成功
        return new Result(true,"登录成功，欢迎您~", data);

    }

}
