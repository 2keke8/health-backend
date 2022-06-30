package com.zky.health.controller;

import com.zky.health.entity.Result;
import com.zky.health.service.SetmealCheckgroupKeyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @description：套餐项目组管理
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-25 11:09
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
@Api(tags = "套餐与检查组接口")//swagger 标注这是一个控制器类
public class SetmealCheckgroupController {

    @Autowired
    SetmealCheckgroupKeyService setmealCheckgroupKeyService;

    /**
     * @description：为套餐添加项目组
     * @param setmealId 套餐id
     * @param groupname 项目组名字
     * @return
     */
    @GetMapping("/api/addgroup/{setmealId}/{groupname}")
    public Result addgroup(@PathVariable("setmealId")Integer setmealId,@PathVariable("groupname")String groupname){

        Result result;

        if(!StringUtils.hasText(groupname)){
            result = Result.error();
            result.setMessage("请输入项目组哦~");
            return result;
        }

        int i = setmealCheckgroupKeyService.addCheckgroup(setmealId, groupname);

        if(i <= 0){
            result = Result.error();
            result.setMessage("新增项目组失败！");
            return result;
        }

        result = Result.success();
        result.setMessage("新增项目组成功~");

        return result;

    }

}
