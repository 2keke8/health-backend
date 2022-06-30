package com.zky.health.dao;

import com.zky.health.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Setmeal record);

    int insertSelective(Setmeal record);

    Setmeal selectByPrimaryKey(Integer id);

    List<Setmeal> queryMeals();

    int updateByPrimaryKeySelective(Setmeal record);

    int updateByPrimaryKey(Setmeal record);
}
