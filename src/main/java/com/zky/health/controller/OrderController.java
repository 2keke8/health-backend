package com.zky.health.controller;

import com.zky.health.entity.Order;
import com.zky.health.entity.Result;
import com.zky.health.service.OrderServcie;
import org.springframework.beans.factory.annotation.Autowired;
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

}
