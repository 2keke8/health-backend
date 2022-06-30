package com.zky.health.service.impl;

import com.zky.health.dao.FoodMapper;
import com.zky.health.entity.Food;
import com.zky.health.service.FoodService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;


@Service
/**
 * @author ：rc
 * @date ：Created in 2022/6/27 9:34
 * @description：
 */
public class FoodServiceImpl implements FoodService {
    @Resource
    FoodMapper foodMapper;
    @Override
    public boolean updateFood(Food food) {
        Integer num;
        if(ObjectUtils.isEmpty(food.getId())){
            //Id为空 新增
            num = foodMapper.insert(food);
        }else{
            //修改
            num = foodMapper.updateByPrimaryKey(food);
        }
        return num==1;
    }

    @Override
    public boolean deleteFood(Integer food_id) {
        return foodMapper.deleteByPrimaryKey(food_id) == 1;
    }

    @Override
    public ArrayList<Food> getFoodList() {
        return foodMapper.selectAll();
    }
}
