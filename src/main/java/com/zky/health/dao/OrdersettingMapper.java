package com.zky.health.dao;

import com.zky.health.entity.Member;
import com.zky.health.entity.Ordersetting;

import java.util.List;

public interface OrdersettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ordersetting record);

    int insertSelective(Ordersetting record);

    Ordersetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ordersetting record);

    int updateByPrimaryKey(Ordersetting record);
}
