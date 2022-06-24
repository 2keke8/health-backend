package com.zky.health.controller;

import com.zky.health.dao.UserMapper;
import com.zky.health.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-23 17:48
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/queryAll")
    public User queryAll(){
        User user = userMapper.selectByPrimaryKey(1);
        return user;
    }

}
