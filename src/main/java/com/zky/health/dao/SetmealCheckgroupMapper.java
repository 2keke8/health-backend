package com.zky.health.dao;

public interface SetmealCheckgroupMapper {
    int deleteByPrimaryKey(SetmealCheckgroupKey key);

    int insert(SetmealCheckgroupKey record);

    int insertSelective(SetmealCheckgroupKey record);
}