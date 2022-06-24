package com.zky.health.entity;

import java.util.Date;

public class Advice {
    private Integer adviceId;

    private Integer userId;

    private Integer healtherId;

    private Date date;

    private String content;

    public Integer getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(Integer adviceId) {
        this.adviceId = adviceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHealtherId() {
        return healtherId;
    }

    public void setHealtherId(Integer healtherId) {
        this.healtherId = healtherId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}