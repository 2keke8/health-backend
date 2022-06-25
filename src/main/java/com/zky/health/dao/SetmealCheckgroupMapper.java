package com.zky.health.dao;

import com.zky.health.entity.SetmealCheckgroupKey;

public interface SetmealCheckgroupMapper {
    int deleteByPrimaryKey(SetmealCheckgroupKey key);

    int insert(SetmealCheckgroupKey record);

    int insertSelective(SetmealCheckgroupKey record);
}