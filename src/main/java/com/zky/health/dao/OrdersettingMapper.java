package com.zky.health.dao;

import com.zky.health.entity.Member;
import com.zky.health.entity.Ordersetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrdersettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ordersetting record);

    int insertSelective(Ordersetting record);

    Ordersetting selectByPrimaryKey(Integer id);

    Ordersetting selectByDate(Date orderdate);
    int updateByPrimaryKeySelective(Ordersetting record);

    int updateByPrimaryKey(Ordersetting record);
}
