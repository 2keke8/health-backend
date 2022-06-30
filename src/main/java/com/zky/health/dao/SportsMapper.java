package com.zky.health.dao;

import com.zky.health.entity.Sports;

import java.util.ArrayList;

public interface SportsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sports record);

    int insertSelective(Sports record);

    Sports selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sports record);

    int updateByPrimaryKey(Sports record);

    ArrayList<Sports> selectAll();
}