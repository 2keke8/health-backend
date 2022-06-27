package com.zky.health.service.impl;

import com.zky.health.dao.DiseaseMapper;
import com.zky.health.entity.Disease;
import com.zky.health.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description：疾病库业务实现类
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service.impl
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-27 09:35
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    DiseaseMapper diseaseMapper;

    @Override
    public int insertDisease(Disease disease) {

        return diseaseMapper.insert(disease);

    }

    @Override
    public int updateDisease(Disease disease) {

        return diseaseMapper.updateByPrimaryKey(disease);

    }

    @Override
    public int deleteDisease(Integer id) {

        return diseaseMapper.deleteByPrimaryKey(id);

    }
}
