package com.zky.health.service;

import com.zky.health.dao.UserMapper;
import com.zky.health.entity.Member;
import com.zky.health.entity.Result;
import com.zky.health.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 用户业务类
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 10:46
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */

public interface UserService {
    //登录逻辑
    int login(String username, String password);

    User selectUserByname(String username);


    String createToken(String username);

    int selectUserRoleId(int userid);

    boolean updateUser(User user, String symbol);

    HashMap<String,Object> selectAll();

    boolean deleteUser(Integer id);
}
