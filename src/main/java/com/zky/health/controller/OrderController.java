package com.zky.health.controller;

import com.zky.health.entity.Order;
import com.zky.health.entity.Result;
import com.zky.health.service.OrderServcie;
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
 * @CreateTime: 2022-06-24 16:28
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
public class OrderController {

    @Autowired
    OrderServcie orderServcie;

    @RequestMapping("/api/queryorders")
    public Result selectAllOrders(){

        Result result;

        List<Order> orders = orderServcie.selectAllOrders();

        result = Result.success();
        result.setMessage("查询预约列表成功");
        result.setData(orders);

        return result;

    }

    @RequestMapping("/api/orderaffirm/{orderid}")
    public Result orderAffirm(@PathVariable("orderid") String orderid){

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

    @RequestMapping("/api/cancelorder/{orderid}")
    public Result cancelorder(@PathVariable("orderid")String orderid){

        Result result;

        int i = orderServcie.cancelOrder(Integer.parseInt(orderid));

        if(i == -1){
            result = Result.error();
            result.setMessage("该会员还没有预约哦~");
            return result;
        }

        if(i > 0){
            result = Result.success();
            result.setMessage("取消预约成功~");
            return result;
        }



        result = Result.error();
        result.setMessage("取消预约失败！请联系管理员");

        return result;
    }
}
