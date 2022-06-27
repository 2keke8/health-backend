package com.zky.health.controller;

import com.zky.health.entity.Disease;
import com.zky.health.entity.Result;
import com.zky.health.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 删除控制器
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-27 09:39
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/disease/")
public class DeleteController {

    @Autowired
    DiseaseService diseaseService;

    /**
     * @description：增添疾病库
     * @param disease
     * @return
     */
    @PostMapping("add")
    public Result addDisease(@RequestBody Disease disease){

        Result result;

        int i = diseaseService.insertDisease(disease);

        if(i <= 0){
            result = Result.error();
            result.setMessage("添加疾病库失败！请联系管理员");
        }

        result = Result.success();
        result.setMessage("添加疾病库成功~");

        return result;

    }
    /**
     * @description：修改疾病库
     * @param disease
     * @return
     */
    @PostMapping("update")
    public Result updateDisease(@RequestBody Disease disease){


        Result result;

        int i = diseaseService.updateDisease(disease);

        if(i <= 0){
            result = Result.error();
            result.setMessage("修改疾病库失败！请联系管理员");
        }

        result = Result.success();
        result.setMessage("修改疾病库成功~");

        return result;

    }

    @DeleteMapping("delete/{id}")
    public Result deleteDisease(@PathVariable("id")Integer id){

        Result result;

        int i = diseaseService.deleteDisease(id);

        if(i <= 0){
            result = Result.error();
            result.setMessage("删除疾病库失败！请联系管理员");
        }

        result = Result.success();
        result.setMessage("删除疾病库成功~");

        return result;

    }

}
