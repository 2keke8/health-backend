package com.zky.health.service;

import com.zky.health.entity.Order;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    Order selectByPrimaryKey(Integer integer);

    int affirmOrder(Integer orderid);

    boolean insertExcel(String fileName, MultipartFile file);

    File downloadModel();

    int cancelOrder(Order order);
}
