package com.zky.health.controller;

import com.zky.health.entity.Checkitem;
import com.zky.health.entity.Result;
import com.zky.health.service.CheckItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description：检查项管理
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-25 10:14
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
@Api(tags = "检查项相关接口")//swagger 标注这是一个控制器类
public class CheckItemController {

    @Autowired
    CheckItemService checkItemService;

    @GetMapping("/api/querygroupitems")
    public Result querygroupitems(){

        Result result;

        List<Checkitem> checkitems = checkItemService.selectAllItems();

        result = Result.success();
        result.setMessage("查询检查项成功~");
        result.setData(checkitems);

        return result;

    }

    @PostMapping("/api/updateitem")
    public Result updateitem(@RequestBody Checkitem checkitem){

        Result result;

        int i = checkItemService.updateitem(checkitem);

        if(i <= 0){
            result = Result.error();
            result.setMessage("更新检查项失败~");
            return result;
        }

        result = Result.success();
        result.setMessage("更新检查项成功");

        return result;

    }

    @GetMapping("/api/deleteitem/{id}")
    public Result deleteitem(@PathVariable("id")Integer id){

        Result result;

        int i = checkItemService.deleteCheckItem(id);

        if(i <= 0){
            result = Result.error();
            result.setMessage("删除检查项失败！");
        }

        result = Result.success();
        result.setMessage("删除检查项成功~");

        return result;

    }


}
