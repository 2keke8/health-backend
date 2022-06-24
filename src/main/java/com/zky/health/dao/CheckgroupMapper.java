package com.zky.health.dao;

import com.zky.health.entity.Checkgroup;

public interface CheckgroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Checkgroup record);

    int insertSelective(Checkgroup record);

    Checkgroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Checkgroup record);

    int updateByPrimaryKey(Checkgroup record);
}