package com.zky.health.dao;

import com.zky.health.entity.Advice;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
@Mapper
public interface AdviceMapper {
    int deleteByPrimaryKey(Integer adviceId);

    int insert(Advice record);

    int insertSelective(Advice record);

    Advice selectByPrimaryKey(Integer adviceId);

    int updateByPrimaryKeySelective(Advice record);

    int updateByPrimaryKeyWithBLOBs(Advice record);

    int updateByPrimaryKey(Advice record);

    ArrayList<Advice> selectAll();
}