package com.zky.health.service;

import com.zky.health.entity.Sports;

import java.util.ArrayList;

public interface SportsService {
    boolean updateSports(Sports sports);

    ArrayList<Sports> getSportsList();

    boolean deleteSports(Integer id);
}
