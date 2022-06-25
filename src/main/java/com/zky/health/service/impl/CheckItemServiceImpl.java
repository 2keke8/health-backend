package com.zky.health.service.impl;

import com.zky.health.dao.CheckitemMapper;
import com.zky.health.entity.Checkitem;
import com.zky.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service.impl
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-25 10:12
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    CheckitemMapper checkitemMapper;

    @Override
    public List<Checkitem> selectAllItems() {
        return checkitemMapper.selectAllItems();
    }
}
