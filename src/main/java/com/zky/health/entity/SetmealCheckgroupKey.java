package com.zky.health.entity;

public class SetmealCheckgroupKey {
    private Integer setmealId;

    private Integer checkgroupId;

    public SetmealCheckgroupKey(Integer setmealId, Integer checkgroupId) {
        this.setmealId = setmealId;
        this.checkgroupId = checkgroupId;
    }

    public Integer getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Integer setmealId) {
        this.setmealId = setmealId;
    }

    public Integer getCheckgroupId() {
        return checkgroupId;
    }

    public void setCheckgroupId(Integer checkgroupId) {
        this.checkgroupId = checkgroupId;
    }
}
