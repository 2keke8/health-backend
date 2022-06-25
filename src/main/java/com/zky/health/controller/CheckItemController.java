package com.zky.health.controller;

import com.zky.health.entity.Checkitem;
import com.zky.health.entity.Result;
import com.zky.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-25 10:14
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
public class CheckItemController {

    @Autowired
    CheckItemService checkItemService;

    @RequestMapping("/api/querygroupitems")
    public Result querygroupitems(){

        Result result;

        List<Checkitem> checkitems = checkItemService.selectAllItems();

        result = Result.success();
        result.setMessage("查询检查项成功~");
        result.setData(checkitems);

        return result;

    }

    @RequestMapping("/api/updateitem")
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

    @RequestMapping("/api/deleteitem/{id}")
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
