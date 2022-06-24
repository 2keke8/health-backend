package com.zky.health.dao;

import com.zky.health.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    Member selectByName(String memberName);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}
