package com.zky.health.service;

import com.zky.health.entity.Disease;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-27 09:34
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
public interface DiseaseService {

    int insertDisease(Disease disease);

    int updateDisease(Disease disease);

    int deleteDisease(Integer id);

    List<Disease> selectAll();

}
