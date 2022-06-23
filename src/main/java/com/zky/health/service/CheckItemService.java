package com.zky.health.service;

import com.zky.health.entity.PageResult;
import com.zky.health.entity.QueryPageBean;
import com.zky.health.pojo.CheckItem;

import java.util.List;

//服务接口
public interface CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    public void deleteById(Integer id);
    public void edit(CheckItem checkItem);
    public CheckItem findById(Integer id);

    List<CheckItem> findAll();
}
