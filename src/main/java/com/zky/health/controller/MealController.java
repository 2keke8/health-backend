package com.zky.health.controller;

import com.zky.health.entity.Result;
import com.zky.health.entity.Setmeal;
import com.zky.health.service.SetMealService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-25 09:17
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
@Api(tags = "套餐相关接口")//swagger 标注这是一个控制器类
public class MealController {

    @Autowired
    SetMealService setMealService;

    @GetMapping("/api/queryMeals")
    public Result queryMeals(){

        Result result = Result.success();
        result.setMessage("查询预约列表成功~");

        List<Setmeal> mealLists = setMealService.queryMeals();

        result.setData(mealLists);

        return result;
    }

    @PostMapping("/api/updatemeal")
    public Result updateMeal(@RequestBody Setmeal setmeal){

        Result result;

        if(setmeal == null){
            result = Result.error();
            result.setMessage("请输入信息~");
        }

        int i = setMealService.updateMeal(setmeal);

        if(i <= 0){
            result = Result.error();
            result.setMessage("修改失败，请联系管理员！");
        }

        result = Result.success();
        result.setMessage("修改成功~");

        return result;
    }


    @GetMapping("/api/deletemeal/{code}")
    public Result deletemeal(@PathVariable("code")Integer code){

        Result result;

        int i = setMealService.deleteMeal(code);

        if(i <= 0){

            result = Result.error();
            result.setMessage("删除套餐失败！");

        }

        result = Result.success();
        result.setMessage("删除套餐成功~");

        return result;
    }
}
