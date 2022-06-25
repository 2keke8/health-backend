package com.zky.health.dao;

import com.zky.health.entity.Checkitem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CheckitemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Checkitem record);

    int insertSelective(Checkitem record);

    Checkitem selectByPrimaryKey(Integer id);

    List<Checkitem> selectAllItems();

    int updateByPrimaryKeySelective(Checkitem record);

    int updateByPrimaryKey(Checkitem record);
}
