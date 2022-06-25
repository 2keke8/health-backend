package com.zky.health.service;

import com.zky.health.entity.Setmeal;

import java.util.List;

/**
 * @Description: 套餐管理业务
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-25 09:08
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
public interface SetMealService {

    List<Setmeal> queryMeals();

}
