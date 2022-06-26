package com.zky.health.controller;

import com.zky.health.entity.Result;
import com.zky.health.service.DataService;
import io.swagger.annotations.Api;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author ：rc
 * @date ：Created in 2022/6/26 9:08
 * @description：工作台
 */
@RestController
@RequestMapping(value = "/api/work/")
@Api(tags = "工作台接口")
public class workTaiController {
    @Resource
    DataService dataService;
    @GetMapping(value = "")
    public Result getInfo(){
        Result result;
        HashMap<String,Object> resMap = dataService.getWorkInfo();
        if(ObjectUtils.isEmpty(resMap)){
            result = Result.error();
            result.setMessage("获取失败，服务器未知错误!");
        }else{
            result = Result.success();
            result.setMessage("获取工作台信息成功");
            result.setData(resMap);
        }
        return result;
    }
}
