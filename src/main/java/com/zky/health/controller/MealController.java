package com.zky.health.controller;

import com.zky.health.entity.Result;
import com.zky.health.entity.Setmeal;
import com.zky.health.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class MealController {

    @Autowired
    SetMealService setMealService;

    @RequestMapping("/api/queryMeals")
    public Result queryMeals(){

        Result result = Result.success();
        result.setMessage("查询预约列表成功~");

        List<Setmeal> mealLists = setMealService.queryMeals();

        result.setData(mealLists);

        return result;
    }

    @RequestMapping("/api/updatemeal")
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

}
