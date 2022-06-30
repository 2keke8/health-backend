package com.zky.health.service.impl;

import com.zky.health.dao.SportsMapper;
import com.zky.health.entity.Sports;
import com.zky.health.service.SportsService;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author ：rc
 * @date ：Created in 2022/6/27 10:07
 * @description：
 */
@Service
public class SportsServiceImpl implements SportsService {
    @Resource
    SportsMapper sportsMapper;
    @Override
    public boolean updateSports(Sports sports) {
        Integer num;
        if(ObjectUtils.isEmpty(sports.getId())){
            //id为空 新增
            num = sportsMapper.insert(sports);
        }else{
            num = sportsMapper.updateByPrimaryKeySelective(sports);
        }
        return num==1;
    }

    @Override
    public ArrayList<Sports> getSportsList() {
        return sportsMapper.selectAll();
    }

    @Override
    public boolean deleteSports(Integer id) {
        return sportsMapper.deleteByPrimaryKey(id)==1;
    }
}
