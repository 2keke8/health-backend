package com.zky.health.service;

import com.zky.health.entity.PageResult;
import com.zky.health.pojo.Setmeal;

import java.util.List;

/**
 * @Author TangBowen
 * @create 2021/6/23 16:32
 */
public interface SetmealService {
    public void add(Setmeal setmeal, Integer[] checkgroupIds);
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    List<Setmeal> findAll();

    Setmeal findById(int id);
}
