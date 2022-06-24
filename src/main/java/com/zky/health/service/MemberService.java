package com.zky.health.service;

import com.zky.health.entity.Member;

/**
 * @Description: 会员业务接口
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 14:02
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
public interface MemberService {

    Member queryByName(String memberName);

}
