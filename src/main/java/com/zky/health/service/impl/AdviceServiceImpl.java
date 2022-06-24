package com.zky.health.service.impl;

import com.zky.health.dao.AdviceMapper;
import com.zky.health.entity.Advice;
import com.zky.health.service.AdviceService;
import org.springframework.stereotype.Service;

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
    public ArrayList<Advice> getAlladvice() {
        ArrayList<Advice>  adviceArrayList = adviceMapper.selectAll();
        return adviceArrayList;
    }
}
