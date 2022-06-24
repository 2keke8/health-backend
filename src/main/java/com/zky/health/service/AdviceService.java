package com.zky.health.service;

import com.zky.health.entity.Advice;

import java.util.ArrayList;

public interface AdviceService {
     String getContentByUserid(Integer userID);

    ArrayList<Advice> getAlladvice();

    boolean addAdvice(Advice advice);

    boolean deleteAdvice(int id);
}
