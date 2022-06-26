package com.zky.health.controller;

import com.zky.health.entity.Order;
import com.zky.health.entity.Result;
import com.zky.health.service.OrderServcie;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 16:28
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
@Api(tags = "预约相关接口")//swagger 标注这是一个控制器类
public class OrderController {

    @Autowired
    OrderServcie orderServcie;

    @GetMapping("/api/queryorders")
    public Result selectAllOrders(){

        Result result;

        List<Order> orders = orderServcie.selectAllOrders();

        result = Result.success();
        result.setMessage("查询预约列表成功");
        result.setData(orders);

        return result;

    }

    @GetMapping("/api/orderaffirm")
    public Result orderAffirm(String orderid){

        Result result;

        int i = orderServcie.affirmOrder(Integer.parseInt(orderid));

        if(i == -1){
            result = Result.error();
            result.setMessage("已经预约过了~");
            return result;
        }

        if(i > 0){
            result = Result.success();
            result.setMessage("确认预约成功~");
            return result;
        }



        result = Result.error();
        result.setMessage("预约失败！请联系管理员");

        return result;
    }
    /*
    * 上传预约表格
    * */
    @PostMapping(value = "/api/importExcel")
    public Result ImportExcel(@RequestParam(value = "orderfiles") MultipartFile orderfiles) {
        Result result;
        if (orderfiles.isEmpty()) {
            result = Result.error();
            result.setMessage("上传表格失败！");
        } else {

            String fileName = orderfiles.getOriginalFilename();
            if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
                result = Result.error();
                result.setMessage("文件格式不正确！");

            }else {
                boolean OK = orderServcie.insertExcel(fileName, orderfiles);
                if (OK) {
                    result = Result.success();
                    result.setMessage("上传成功！");
                } else {
                    result = Result.error();
                    result.setMessage("发生服务器内部错误，上传失败!");
                }
            }
        }
        return  result;
    }

    /*
    * 下载表格模板
    * */
    @PostMapping(value = "/api/downloadModel")
    public Result downloadModel(){
        Result result;
        File model =  orderServcie.downloadModel();
        if(ObjectUtils.isEmpty(model)){
            result = Result.error();
            result.setMessage("出现未知错误！模板下载失败");
        }else{
            result = Result.success();
            result.setData(model);
            result.setMessage("获取模板成功！！！");
        }

        return  result;
    }



}
