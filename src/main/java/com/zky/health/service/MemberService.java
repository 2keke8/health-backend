package com.zky.health.service;

import com.zky.health.entity.Member;

import java.util.List;

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

    List<Member> selectAllMembers();

    int insertMember(Member member);

    int deleteMember(int userid);

    /**
     * 批量删除会员
     * @param membersId 会员的id
     * @return 返回影响的行数
     */
    int deleteMembers(List<Integer> membersId);
}
