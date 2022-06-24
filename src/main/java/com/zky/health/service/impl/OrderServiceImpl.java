package com.zky.health.service.impl;

import com.zky.health.dao.OrderMapper;
import com.zky.health.entity.Order;
import com.zky.health.service.OrderServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service.impl
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 16:26
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class OrderServiceImpl implements OrderServcie {

    @Autowired
    OrderMapper orderMapper;

    public List<Order> selectAllOrders(){

        return orderMapper.selectAllOrders();

    }

}
