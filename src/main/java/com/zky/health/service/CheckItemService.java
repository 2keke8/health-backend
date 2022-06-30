package com.zky.health.service;

import com.zky.health.entity.Checkitem;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-25 10:09
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
public interface CheckItemService {

    List<Checkitem> selectAllItems();

    int updateitem(Checkitem checkitem);

    int deleteCheckItem(Integer id);

}
