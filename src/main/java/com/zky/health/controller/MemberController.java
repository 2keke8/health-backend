package com.zky.health.controller;

import com.zky.health.entity.Member;
import com.zky.health.entity.Result;
import com.zky.health.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 会员控制器
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 14:35
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/api/querybyname")
    public Result queryByName(String membername){

        Result result;
        if(!StringUtils.hasText(membername)){
            result = Result.error();
            result.setMessage("请输入用户名~");
            return result;
        }

        Member member = memberService.queryByName(membername);
        if(member == null){
            result = Result.error();
            result.setMessage("此用户名不存在");
            return result;
        }

        result = Result.success();
        result.setMessage("查询用户成功");
        result.setData(member);
        return result;
    }

}
