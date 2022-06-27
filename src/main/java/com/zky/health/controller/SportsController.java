package com.zky.health.controller;

import com.zky.health.entity.Food;
import com.zky.health.entity.Result;
import com.zky.health.entity.Sports;
import com.zky.health.service.FoodService;
import com.zky.health.service.SportsService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author ：rc
 * @date ：Created in 2022/6/27 10:07
 * @description：运动项目
 */

@RestController
@RequestMapping(value = "/api/sports")
public class SportsController {

    @Resource
    SportsService sportsService;

    /*
    * 新增/修改
    * */
    @PostMapping("/")
    public Result updateFood(@RequestBody Sports sports){
        Result result;

        boolean OK = sportsService.updateSports(sports);
        if(OK){
            result = Result.success();
            result.setMessage("更新体育项目信息成功!");
        }else{
            result = Result.error();
            result.setMessage("更新体育项目信息失败！");
        }

        return result;
    }

    /*
    * 查询列表
    * */
    @GetMapping(value ="/get")
    public Result getSportsList(){
        Result result;
        ArrayList<Sports> sportsArrayList=sportsService.getSportsList();
        if(ObjectUtils.isEmpty(sportsArrayList)){
//            出错
            result = Result.error();
            result.setMessage("查询出错!");
        }else{
            result = Result.success();
            result.setData(sportsArrayList);
        }
        return  result;
    }

    /*
    * 删除
    * */

    @GetMapping(value ="/delete/{id}")
    public Result deleteFood(@PathVariable(value = "id") Integer id){
        Result result;
        boolean OK = sportsService.deleteSports(id);
        if(OK){
            result = Result.success();
            result.setMessage("删除体育项目信息成功!");
        }else{
            result = Result.error();
            result.setMessage("删除体育项目信息失败！");
        }
        return  result;
    }
}
