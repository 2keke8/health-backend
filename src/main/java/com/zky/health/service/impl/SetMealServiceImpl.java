package com.zky.health.service.impl;

import com.zky.health.dao.SetmealMapper;
import com.zky.health.entity.Setmeal;
import com.zky.health.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service.impl
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-25 09:08
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    SetmealMapper setmealMapper;

    @Override
    public List<Setmeal> queryMeals() {

        return setmealMapper.queryMeals();

    }

    @Override
    public int updateMeal(Setmeal setmeal) {

        return setmealMapper.updateByPrimaryKey(setmeal);

    }
}
