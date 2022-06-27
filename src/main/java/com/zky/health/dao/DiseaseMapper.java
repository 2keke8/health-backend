package com.zky.health.dao;

import com.zky.health.entity.Disease;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiseaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Disease record);

    int insertSelective(Disease record);

    Disease selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Disease record);

    int updateByPrimaryKey(Disease record);
}
