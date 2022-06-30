package com.zky.health.utils;

import com.zky.health.entity.User;
import org.springframework.stereotype.Component;

/**
 * @Description: 持有用户信息。用来代替session对象，实现线程隔离
 * @BelongsProject: community
 * @BelongsPackage: com.zky.util
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-02-08 16:16
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUsers(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }
}
