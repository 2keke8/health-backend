package com.zky.health.service.impl;

import com.zky.health.dao.OrdersettingMapper;
import com.zky.health.entity.Ordersetting;
import com.zky.health.service.OrderSettingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 预约设置实现类
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service.impl
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-26 19:41
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    OrdersettingMapper ordersettingMapper;

    @Override
    public int insertOrderSetting(Ordersetting ordersetting) {

        return ordersettingMapper.insert(ordersetting);

    }

    @Override
    public Ordersetting queryOrderSettingByDate(Date orderdate) {
        return ordersettingMapper.selectByDate(orderdate);
    }

}
