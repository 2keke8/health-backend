package com.zky.health.service.impl;

import com.zky.health.constant.MyConstant;
import com.zky.health.dao.UserMapper;
import com.zky.health.dao.UserRoleMapper;
import com.zky.health.entity.User;
import com.zky.health.service.UserService;
import com.zky.health.utils.JwtUtil;
import com.zky.health.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

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

    // 查询用户列表
    @Override
    public HashMap<String,Object> selectAll() {
        HashMap<String,Object> resMap = new HashMap<>();
        ArrayList<User> users = userMapper.selectAll();
        ArrayList<String> roles = new ArrayList<>();
        for(User user : users){
            //查询用户角色
            Integer roleId = selectUserRoleId(user.getId());
            //添加用户信息
            users.add(user);
            //添加角色信息
            if(roleId == 1){
            //系统管理员
                roles.add("系统管理员");
            }else {
                //健康管理师
                roles.add("健康管理师");
            }
        }

        return resMap;

    }

    @Override
    public boolean deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id)==1;
    }

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
        System.out.println(MD5Utils.md5(password));
        System.out.println(user.getPassword());
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

    /*
    * 更新用户信息
    * */
    @Override
    public boolean updateUser(User user, String symbol) {
        Integer num ;
//        判断添加还是更新数据
        if("add".equals(symbol)){
            num = userMapper.insert(user);
        }else if("update".equals(symbol)){
            //判断用户名是否为空
            if(ObjectUtils.isEmpty(user.getId())){
                num = 0;
            }else{
                num = userMapper.updateByPrimaryKey(user);
            }
        }else{
            return false;
        }
        return num==1;

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
