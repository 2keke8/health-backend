package com.zky.health.dao;

import com.zky.health.entity.Checkgroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckgroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Checkgroup record);

    int insertSelective(Checkgroup record);

    Checkgroup selectByPrimaryKey(Integer id);

    Checkgroup selectByName(String name);

    int updateByPrimaryKeySelective(Checkgroup record);

    int updateByPrimaryKey(Checkgroup record);
}
