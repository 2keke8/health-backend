package com.zky.health.service.impl;

import com.zky.health.dao.AdviceMapper;
import com.zky.health.entity.Advice;
import com.zky.health.entity.User;
import com.zky.health.service.AdviceService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author ：rc
 * @date ：Created in 2022/6/24 10:54
 * @description：
 */
@Service
public class AdviceServiceImpl implements AdviceService {
    @Resource
    AdviceMapper adviceMapper;

    @Override
    public String getContentByUserid(Integer userID) {
        Advice advice = adviceMapper.selectByPrimaryKey(userID);
        if(ObjectUtils.isEmpty(advice)){
            return "";
        }else{
            return advice.getContent();
        }

    }

    @Override
    public ArrayList<Advice> getAlladvice() {
        ArrayList<Advice>  adviceArrayList = adviceMapper.selectAll();
        return adviceArrayList;
    }

    @Override
    public boolean addAdvice(Advice advice) {
        Integer num = adviceMapper.insertSelective(advice);
        return num==1;
    }

    @Override
    public boolean deleteAdvice(int id) {
        Integer num = adviceMapper.deleteByPrimaryKey(id);
        return num==1;
    }
}
