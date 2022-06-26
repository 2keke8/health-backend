package com.zky.health.controller;

import com.zky.health.entity.Result;
import com.zky.health.entity.User;
import com.zky.health.service.DataService;
import com.zky.health.service.UserService;
import com.zky.health.utils.HostHolder;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @description：用户控制器
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-23 17:48
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@Api(tags = "用户相关接口")//swagger 标注这是一个控制器类
//@CrossOrigin(origins = "*",allowCredentials = "true")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    DataService dataService;
    @PostMapping("/api/login")
    public Result login(@RequestBody User user){


        Result result;

        //判断用户名和密码是否为空
        if(!StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword())){
            result = Result.error();
            result.setMessage("用户名和密码不能为空哦~");
            return result;
        }

        //0：用户不存在  -1：密码错误 >0：验证成功
        int res = userService.login(user.getUsername(), user.getPassword());
        if(res == 0){
            result = Result.error();
            result.setMessage("用户名不存在，请联系管理员~");
            return result;
        }
        //判断密码是否正确
        if(res == -1){
            result = Result.error();
            result.setMessage("密码错误，请输入正确密码！");
            return result;
        }

        // 检查结束， 没有问题， 记录用户登录信息
        dataService.recordMemberUV(String.valueOf(user.getId()));
        // 记录DAU
        if (user != null) {
            dataService.recordMemberDAU(user.getId().intValue());
        }

        //封装返回结果
        HashMap<String, Object> data = new HashMap<>();
        User resuser = userService.selectUserByname(user.getUsername());
        String token = userService.createToken(resuser.getUsername());
        data.put("user",resuser);
        data.put("token",token);

        //登录成功
        result = Result.success();
        result.setMessage("登录成功，欢迎您~");
        result.setData(data);
        return result;
    }

}
