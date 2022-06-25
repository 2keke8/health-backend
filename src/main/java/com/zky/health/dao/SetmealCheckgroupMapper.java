package com.zky.health.dao;

import com.zky.health.entity.SetmealCheckgroupKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealCheckgroupMapper {
    int deleteByPrimaryKey(SetmealCheckgroupKey key);

    int insert(SetmealCheckgroupKey record);

    int insertSelective(SetmealCheckgroupKey record);

    int selectGroupIdByMealId(Integer setmealId);

    int selectByName(String name);
}
