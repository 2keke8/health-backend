package com.zky.health.dao;

import com.zky.health.entity.Member;
import com.zky.health.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAllOrders();

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    ArrayList<Order> selectByStu(String stu);
}
