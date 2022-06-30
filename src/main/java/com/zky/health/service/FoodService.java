package com.zky.health.service;

import com.zky.health.entity.Food;

import java.util.ArrayList;

public interface FoodService {
    boolean updateFood(Food food);

    boolean deleteFood(Integer food_id);

    ArrayList<Food> getFoodList();
}
