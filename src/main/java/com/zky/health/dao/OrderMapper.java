package com.zky.health.dao;

import com.zky.health.entity.Member;
import com.zky.health.entity.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAllOrders();

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
