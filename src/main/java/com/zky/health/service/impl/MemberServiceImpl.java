package com.zky.health.service.impl;

import com.zky.health.dao.MemberMapper;
import com.zky.health.entity.Member;
import com.zky.health.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 会员实现类
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.service.impl
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 14:03
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Member queryByName(String membername) {
        return memberMapper.selectByName(membername);
    }

    @Override
    public List<Member> selectAllMembers() {
        return memberMapper.selectAllMembers();
    }

    @Override
    public int insertMember(Member member) {
        return memberMapper.insert(member);
    }

    @Override
    public int deleteMember(int userid) {
        return memberMapper.deleteByPrimaryKey(userid);
    }
}
