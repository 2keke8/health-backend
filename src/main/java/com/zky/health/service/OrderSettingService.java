package com.zky.health.service;

import com.zky.health.dao.OrdersettingMapper;
import com.zky.health.entity.Ordersetting;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @description：预约设置
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-26 19:39
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
public interface OrderSettingService {

    int insertOrderSetting(Ordersetting ordersetting);

    Ordersetting queryOrderSettingByDate(Date orderdate);

}
