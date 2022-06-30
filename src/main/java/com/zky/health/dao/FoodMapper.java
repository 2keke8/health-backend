package com.zky.health.dao;

import com.zky.health.entity.Food;

import java.util.ArrayList;

public interface FoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);

    ArrayList<Food> selectAll();
}