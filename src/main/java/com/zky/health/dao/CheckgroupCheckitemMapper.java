package com.zky.health.dao;

import com.zky.health.entity.CheckgroupCheckitemKey;

public interface CheckgroupCheckitemMapper {
    int deleteByPrimaryKey(CheckgroupCheckitemKey key);

    int insert(CheckgroupCheckitemKey record);

    int insertSelective(CheckgroupCheckitemKey record);
}