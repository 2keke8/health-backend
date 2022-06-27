package com.zky.health.controller;

import com.zky.health.entity.Food;
import com.zky.health.entity.Result;
import com.zky.health.service.FoodService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author ：rc
 * @date ：Created in 2022/6/27 9:30
 * @description：膳食库
 */

@RestController
@RequestMapping(value = "/api/food")
public class FoodController {
    @Resource
    FoodService foodService;

    /*
    * 新增/修改
    * */
    @PostMapping(value ="/")
     public Result updateFood(@RequestBody Food food){
        Result result;
        boolean OK = foodService.updateFood(food);
        if(OK){
            result = Result.success();
            result.setMessage("更新膳食信息成功!");
        }else{
            result = Result.error();
            result.setMessage("更新膳食信息失败！");
        }
        return  result;
    }

    /*
    * 查询列表
    * */
    @GetMapping(value ="get")
    public Result getFoodList(){
        Result result;
        ArrayList<Food> foodArrayList=foodService.getFoodList();
        if(ObjectUtils.isEmpty(foodArrayList)){
//            出错
            result = Result.error();
            result.setMessage("查询出错!");
        }else{
            result = Result.success();
            result.setData(foodArrayList);
        }
        return  result;
    }

    /*
    * 删除
    * */
    @GetMapping(value ="delete/{food_id}")
    public Result deleteFood(@PathVariable(value = "food_id") Integer food_id){
        Result result;
        boolean OK = foodService.deleteFood(food_id);
        if(OK){
            result = Result.success();
            result.setMessage("删除膳食信息成功!");
        }else{
            result = Result.error();
            result.setMessage("删除膳食信息失败！");
        }
        return  result;
    }
}
