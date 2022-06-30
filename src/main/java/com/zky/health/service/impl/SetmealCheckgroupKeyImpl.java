package com.zky.health.service.impl;

import com.zky.health.dao.CheckgroupMapper;
import com.zky.health.dao.SetmealCheckgroupMapper;
import com.zky.health.entity.Checkgroup;
import com.zky.health.entity.SetmealCheckgroupKey;
import com.zky.health.service.SetmealCheckgroupKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service.impl
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-25 10:52
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class SetmealCheckgroupKeyImpl implements SetmealCheckgroupKeyService {

    @Autowired
    SetmealCheckgroupMapper setmealCheckgroupMapper;

    @Autowired
    CheckgroupMapper checkgroupMapper;

    @Override
    public int addCheckgroup(Integer setmealId, String groupname) {

        Checkgroup checkgroup = checkgroupMapper.selectByName(groupname);
        SetmealCheckgroupKey setmealCheckgroupKey = new SetmealCheckgroupKey(setmealId, checkgroup.getId());

        return setmealCheckgroupMapper.insert(setmealCheckgroupKey);
    }
}
