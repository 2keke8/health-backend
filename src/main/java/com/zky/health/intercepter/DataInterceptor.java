package com.zky.health.intercepter;

import com.zky.health.entity.User;
import com.zky.health.service.DataService;
import com.zky.health.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @BelongsProject: community
 * @BelongsPackage: com.zky.controller.intercepter
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-03-08 20:14
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class DataInterceptor implements HandlerInterceptor {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    DataService dataService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = hostHolder.getUser();

        if(user == null) return true;
        // 统计UV
        dataService.recordMemberUV(String.valueOf(user.getId()));

        // 统计DAU

        if (user != null) {
            dataService.recordMemberDAU(user.getId());
        }
        return true;
    }
}
