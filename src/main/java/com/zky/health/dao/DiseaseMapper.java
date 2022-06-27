package com.zky.health.dao;

import com.zky.health.entity.Disease;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiseaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Disease record);

    int insertSelective(Disease record);

    Disease selectByPrimaryKey(Integer id);

    List<Disease> selectAll();

    int updateByPrimaryKeySelective(Disease record);

    int updateByPrimaryKey(Disease record);
}
