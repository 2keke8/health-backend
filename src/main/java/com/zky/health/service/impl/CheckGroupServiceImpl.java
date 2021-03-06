package com.pangzhao.service.impl;

import com.pangzhao.entity.PageResult;
import com.pangzhao.mapper.CheckGroupAndCheckItemMapper;
import com.pangzhao.mapper.CheckGroupMapper;
import com.pangzhao.mapper.CheckItemMapper;
import com.pangzhao.mapper.SetMealAndCheckgroupMapper;
import com.pangzhao.pojo.CheckGroup;
import com.pangzhao.pojo.CheckGroupAndCheckItem;
import com.pangzhao.pojo.CheckItem;
import com.pangzhao.pojo.SetMealAndCheckgroup;
import com.pangzhao.service.CheckGroupService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;
    @Autowired
    private CheckGroupAndCheckItemMapper checkGroupAndCheckItemMapper;
    @Autowired
    private CheckItemMapper checkItemMapper;
    @Autowired
    private SetMealAndCheckgroupMapper setMealAndCheckgroupMapper;

    @Override
    public PageResult findByPage(Integer currentPage, Integer pageSize, String queryString) {

        Page<CheckGroup> checkGroups;

        Example example = new Example(CheckGroup.class);
        Example.Criteria criteria = example.createCriteria();

        if (queryString!=null&&queryString!=""){
            criteria.orLike("code","%"+queryString+"%").orLike("name","%"+queryString+"%").orLike("helpCode","%"+queryString+"%");
            PageHelper.startPage(currentPage,pageSize);
            checkGroups = (Page<CheckGroup>) checkGroupMapper.selectByExample(example);
        }else{
            PageHelper.startPage(currentPage,pageSize);
            checkGroups = (Page<CheckGroup>) checkGroupMapper.selectAll();
        }

        return new PageResult(checkGroups.getTotal(),checkGroups.getResult());
    }

    //??????id???????????????
    @Transactional
    @Override
    public List<CheckItem> findById(Integer id) {
        Example example = new Example(CheckGroupAndCheckItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("checkGroupId",id);

        List<CheckGroupAndCheckItem> checkGroupAndCheckItems = checkGroupAndCheckItemMapper.selectByExample(example);
        ArrayList<Integer> ids = new ArrayList<>();
        for (CheckGroupAndCheckItem groupAndCheckItem : checkGroupAndCheckItems) {
            ids.add(groupAndCheckItem.getCheckItemId());
        }

        ArrayList<CheckItem> checkItems = new ArrayList<>();
        for (Integer checkItemId : ids) {
            CheckItem checkItem = checkItemMapper.selectByPrimaryKey(checkItemId);
            checkItems.add(checkItem);
        }

        return checkItems;
    }

    //???????????????
    @Override
    public CheckGroup findCheckGroupById(Integer id) {
        return checkGroupMapper.selectByPrimaryKey(id);
    }

    //???????????????
    @Transactional
    @Override
    public void addCheckGroup(CheckGroup checkGroup, Integer[] ids) {
        //????????????
        checkGroupMapper.insert(checkGroup);

        //??????checkGroup??????
        CheckGroup checkGroup2 = checkGroupMapper.selectOne(checkGroup);
        Integer checkGroupId = checkGroup2.getId();

        for (Integer id : ids) {
            CheckGroupAndCheckItem checkGroupAndCheckItem = new CheckGroupAndCheckItem(checkGroupId, id);
            checkGroupAndCheckItemMapper.insert(checkGroupAndCheckItem);
        }
    }

    //???????????????
    @Transactional
    @Override
    public void updateCheckGroup(CheckGroup checkGroup, Integer[] ids) {
        checkGroupMapper.updateByPrimaryKey(checkGroup);
        Integer checkGroupId = checkGroup.getId();

        //???????????????id??????????????????????????????id
        Example example = new Example(CheckGroupAndCheckItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("checkGroupId",checkGroupId);
        criteria.andNotIn("checkItemId", Arrays.asList(ids));
        List<CheckGroupAndCheckItem> checkGroupAndCheckItems = checkGroupAndCheckItemMapper.selectByExample(example);
        for (CheckGroupAndCheckItem checkGroupAndCheckItem : checkGroupAndCheckItems) {
            checkGroupAndCheckItemMapper.delete(checkGroupAndCheckItem);
        }
    }

    //???????????????
    @Transactional
    @Override
    public void deleteCheckGroup(Integer id) {

        //???????????????????????????????????????????????????
        Example example1 = new Example(SetMealAndCheckgroup.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("checkGroupId",id);
        int count = setMealAndCheckgroupMapper.selectCountByExample(example1);
        if (count>0){
            throw new RuntimeException("???????????????????????????????????????,????????????");
        }

        //??????????????????????????????
        Example example = new Example(CheckGroupAndCheckItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("checkGroupId",id);
        List<CheckGroupAndCheckItem> checkGroupAndCheckItems = checkGroupAndCheckItemMapper.selectByExample(example);

        for (CheckGroupAndCheckItem checkGroupAndCheckItem : checkGroupAndCheckItems) {
            checkGroupAndCheckItemMapper.delete(checkGroupAndCheckItem);
        }

        //??????????????????
        checkGroupMapper.deleteByPrimaryKey(id);
    }

    //????????????
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupMapper.selectAll();
    }

}
