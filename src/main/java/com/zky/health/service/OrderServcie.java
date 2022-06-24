package com.zky.health.service;

import com.zky.health.entity.Order;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 16:22
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
public interface OrderServcie {

    List<Order> selectAllOrders();

    int affirmOrder(int orderid);

    int cancelOrder(int orderid);

}
