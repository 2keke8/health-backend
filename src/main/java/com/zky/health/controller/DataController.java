package com.zky.health.controller;

import com.zky.health.entity.Result;
import com.zky.health.service.DataService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description:
 * @BelongsProject: community
 * @BelongsPackage: com.zky.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-03-08 20:26
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
@Api(tags = "统计数据相关接口")//swagger 标注这是一个控制器类
public class DataController {
    @Autowired
    private DataService dataService;

    // 统计活跃用户
    @GetMapping(path = "/api/data/dau")
    public Result getDAU(@DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {

        Result result;

        if(start == null || end == null){
            result = Result.error();
            result.setMessage("查询失败，请联系管理员！");
            return result;
        }

        long MemberDau = dataService.calculateMemberDAU(start,end);

        result = Result.success();
        result.setMessage("查询活跃用户成功");
        result.setData(MemberDau);

        return result;
    }
}