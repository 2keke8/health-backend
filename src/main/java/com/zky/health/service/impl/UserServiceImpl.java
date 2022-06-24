package com.zky.health.service.impl;

import com.zky.health.constant.MyConstant;
import com.zky.health.dao.UserMapper;
import com.zky.health.dao.UserRoleMapper;
import com.zky.health.entity.Result;
import com.zky.health.entity.User;
import com.zky.health.service.UserService;
import com.zky.health.utils.JwtUtil;
import com.zky.health.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Description: 用户业务实现类
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service.impl
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 10:48
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    /**
     *
     * @param username
     * @param password
     * @return 0：用户不存在  -1：密码错误  1：登录成功
     */
    @Override
    public int login(String username, String password) {

        //查询用户
        User user = userMapper.selectByUsername(username);
        //判断用户名是否存在
        if(user == null){
            return 0;
        }
        //判断密码是否正确
        if(!MD5Utils.md5(password).equals(user.getPassword())){
            return -1;
        }
        //登录成功
        return 1;
    }

    @Override
    public User selectUserByname(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int selectUserRoleId(int userid) {
        return userRoleMapper.selectUserRoleId(userid);
    }

    @Override
    public String createToken(String username) {
        User user = selectUserByname(username);
        //查询用户的权限
        int roleId = selectUserRoleId(user.getId());
        String token = JwtUtil.createToken(username, roleId, MyConstant.LOGIN_TOKEN);
        return token;
    }
}
