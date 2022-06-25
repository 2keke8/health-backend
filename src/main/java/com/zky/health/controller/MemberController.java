package com.zky.health.controller;

import com.zky.health.entity.Member;
import com.zky.health.entity.Result;
import com.zky.health.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
            result.setMessage("请输入会员名~");
            return result;
        }

        Member member = memberService.queryByName(membername);
        if(member == null){
            result = Result.error();
            result.setMessage("此会员名不存在");
            return result;
        }

        result = Result.success();
        result.setMessage("查询会员成功");
        result.setData(member);
        return result;
    }

    @RequestMapping("/api/addmember")
    public Result addMember(@RequestBody Member member){

        Result result;
        int i = memberService.insertMember(member);
        if(i <= 0){
            result = Result.error();
            result.setMessage("系统错误，插入失败！");
            return result;
        }

        member.setRegtime(new Date());

        result = Result.success();
        result.setMessage("插入会员成功！");
        return result;
    }

    @RequestMapping("/api/deletemember/{memberid}")
    public Result deleteUser(@PathVariable("memberid") String id){

        int memberid = Integer.parseInt(id);

        Result result;

        int i = memberService.deleteMember(memberid);
        if(i <= 0){
            result = Result.error();
            result.setMessage("删除会员失败！");
            return result;
        }

        result = Result.success();
        result.setMessage("删除会员成功");

        return result;
    }

    @RequestMapping("/api/queryallmembers")
    public Result queryAllMembers(){

        ArrayList<HashMap> resList = new ArrayList<>();
        Date date = new Date();
        int currentYear = date.getYear();

        List<Member> members = memberService.selectAllMembers();
        for (Member member : members) {
            HashMap<Object, Object> resMap = new HashMap<>();
            int age;
            Date birthday = member.getBirthday();
            int year = birthday.getYear();
            age = currentYear - year + 1;
            resMap.put("age",age);
            resMap.put("member",member);
            resList.add(resMap);
        }

        Result result = Result.success();
        result.setData(resList);
        result.setMessage("查询会员列表成功");

        return result;
    }

}
