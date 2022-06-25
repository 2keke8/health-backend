package com.zky.health.controller;

import com.zky.health.entity.Checkitem;
import com.zky.health.entity.Result;
import com.zky.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
//
//    @RequestMapping("/api/queryitem/{code}")
//    public Result queryitem(@PathVariable("code") String code){
//
//        Result result;
//
//        checkItemService.selectAllItems()
//
//    }

}
