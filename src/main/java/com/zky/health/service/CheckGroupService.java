package com.zky.health.service;

import com.zky.health.entity.PageResult;
import com.zky.health.pojo.CheckGroup;

import java.util.List;


public interface CheckGroupService {

    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    List<CheckGroup> findAll();
}
