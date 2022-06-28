package com.zky.health.controller;

import com.zky.health.entity.Member;
import com.zky.health.entity.Result;
import com.zky.health.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @description：会员设置
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 14:35
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
@Api(tags = "会员相关接口")//swagger 标注这是一个控制器类
public class MemberController {

    @Autowired
    MemberService memberService;

    /**
     * @description：通过会员名查询会员
     * @param membername
     * @return
     */
    @GetMapping("/api/querybyname")
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

    /**
     * @description：增加会员
     * @param member
     * @return
     */
    @PostMapping("/api/addmember")
    public Result addMember(@RequestBody Member member){

        Result result;
        int i = memberService.insertMember(member);
        if(i <= 0){
            result = Result.error();
            result.setMessage("系统错误，插入失败！");
            return result;
        }
        // 设置插入会员的时间
        member.setRegtime(new Date());

        result = Result.success();
        result.setMessage("插入会员成功！");
        return result;
    }

    /**
     * @description：通过会员id删除会员
     * @param id
     * @return
     */
    @GetMapping("/api/deletemember/{memberid}")
    public Result deleteMember(@PathVariable("memberid") String id){

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

    /**
     * @description：删除会员
     * @param membersId 会员id
     * @return
     */
    @PostMapping("/api/deletemembers")
    public Result deleteMember(@RequestBody List<Integer> membersId){


        Result result;

        int i = memberService.deleteMembers(membersId);
        if(i <= 0){
            result = Result.error();
            result.setMessage("批量删除会员失败！");
            return result;
        }

        result = Result.success();
        result.setMessage("批量删除会员成功");

        return result;
    }

    /**
     * @description：更新会员信息
     * @param member 会员信息
     * @return
     */
    @PostMapping("/api/updatemember")
    public Result updateMember(@RequestBody Member member){


        Result result;

        int i = memberService.updateMembers(member);
        if(i <= 0){
            result = Result.error();
            result.setMessage("修改会员信息失败！");
            return result;
        }

        result = Result.success();
        result.setMessage("修改会员信息成功");

        return result;

    }

    /**
     * @description：查询所有会员
     * @return：包含map集合：会员名，会员信息
     */
    @GetMapping("/api/queryallmembers")
    public Result queryAllMembers(){

        ArrayList<HashMap> resList = new ArrayList<>();
        Date date = new Date();

        // 得到当前的年份， 用于计算年龄
        int currentYear = date.getYear();

        List<Member> members = memberService.selectAllMembers();
        for (Member member : members) {
            HashMap<Object, Object> resMap = new HashMap<>();
            int age;
            Date birthday = member.getBirthday();
            int year = birthday.getYear();

            // 计算年龄， 当前年份减去生日的年份加1
            age = currentYear - year + 1;
            resMap.put("age",age);
            String[] strings = {"2022/4/1","2022/5/22","2022/6/26","2022/6/27","2022/6/26","2022/6/27","2022/6/26","2022/6/27","2022/6/26","2022/6/27"};
            resMap.put("reg",strings[new Random().nextInt(9)]);
            if(new Random().nextInt(2)==1){
                resMap.put("flag","报告已上传");
            }else{
                resMap.put("flag","报告未上传");
            }

            if(new Random().nextInt(2)==1){
                resMap.put("taocan","普通体检项");
            }else{
                resMap.put("taocan","豪华体检项");
            }

            resMap.put("member",member);
            resList.add(resMap);
        }

        Result result = Result.success();
        result.setData(resList);
        result.setMessage("查询会员列表成功");

        return result;
    }

}
