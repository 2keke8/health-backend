package com.zky.health.service;

import com.zky.health.entity.Advice;

import java.util.ArrayList;
import java.util.HashMap;

public interface QuestionService {

    ArrayList<HashMap> getReplyListByType(Integer question_id);

    boolean getTestStu(int parseInt, int parseInt1);
}
