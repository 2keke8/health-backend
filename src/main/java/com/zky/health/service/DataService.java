package com.zky.health.service;

import com.zky.health.constant.MyConstant;
import com.zky.health.dao.AdviceMapper;
import com.zky.health.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @BelongsProject: community
 * @BelongsPackage: com.zky.service
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-03-08 19:42
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@Component
public class DataService extends MyConstant{

    @Resource
    AdviceMapper adviceMapper;
    @Resource
    OrderMapper orderMapper;
    @Resource
    AdviceService adviceService;
    @Resource
    OrderServcie orderServcie;
    @Resource
    MemberService memberService;
    @Resource
    QuestionService questionService;

    @Autowired
    RedisTemplate redisTemplate;

    private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

    // 将指定ip计入uv
    public void recordMemberUV(String memberid) {
        String redisKey = MyConstant.getUVKey(df.format(new Date()));
        redisTemplate.opsForHyperLogLog().add(redisKey, memberid);
    }

    // 统计指定日期范围内的uv
    public long calculateMemberUV(Date start, Date end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 整理该日期范围内的key
        List<String> keyList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        while (!calendar.getTime().after(end)) {
            String key = MyConstant.getUVKey(df.format(calendar.getTime()));
            keyList.add(key);
            calendar.add(Calendar.DATE, 1);
        }

        // 合并这些日期内的数据
        String redisKey = MyConstant.getUVKey(df.format(start), df.format(end));
        redisTemplate.opsForHyperLogLog().union(redisKey, keyList.toArray());

        // 返回统计后的结果
        return redisTemplate.opsForHyperLogLog().size(redisKey);
    }

    // 将指定用户计入DAU
    public void recordMemberDAU(int userid) {
        String redisKey = MyConstant.getDAUKey(df.format(new Date()));
        //memberid必须为long
        redisTemplate.opsForValue().setBit(redisKey, userid, true);
    }

    public long calculateMemberDAU(String time){
        if(!StringUtils.hasText(time)){
            throw new IllegalArgumentException("参数不能为空!");
        }
        return (long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                Long aLong = connection.bitCount(time.getBytes());
                return aLong;
            }
        });
    }

    /**
     *
     * @descreption 查询时间段的会员数
     * @param start
     * @param end
     * @param flag
     * @return 返回每天对应的会员数据量
     */
    public HashMap<String, Integer> calculateMemberDAU1(Date start, Date end, int flag) {

        long counts = calculateMemberDAU(start, end);
        //返回map数据
        HashMap<String, Integer> hashMap = new HashMap<>();

        String dfstart = df.format(start);
        String dfend = df.format(start);

        int startInt = Integer.parseInt(dfstart);
        int endInt = Integer.parseInt(dfend);

        for (int i = startInt; i <= endInt; i++) {

            String time = String.valueOf(i);
            long dau = calculateMemberDAU(String.valueOf(i));
            hashMap.put(time, (int) dau);

        }

        hashMap.put("members", (int) counts);

        return hashMap;

    }

    // 统计指定日期范围内的DAU 返回总数
    public long calculateMemberDAU(Date start, Date end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 整理该日期范围内的key
        List<byte[]> keyList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        while (!calendar.getTime().after(end)) {
            String key = MyConstant.getDAUKey(df.format(calendar.getTime()));
            keyList.add(key.getBytes());
            calendar.add(Calendar.DATE, 1);
        }

        // 进行OR 运算
        return (long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                String redisKey = MyConstant.getDAUKey(df.format(start), df.format(end));
                connection.bitOp(RedisStringCommands.BitOperation.OR,
                        redisKey.getBytes(), keyList.toArray(new byte[0][0]));
                return connection.bitCount(redisKey.getBytes());
            }
        });
    }

    /*
    * 获取工作台的信息
    *
    *   今日预约人数：今日未执行：今日已执行：今日评估人数：
    * 预约人数-： 未执行-： 已执行-： 评估人数-
    * 问卷评估- 预约人数- 干预方案- 干预追踪-没有
    * 今日新增 今日登录 会员总数-
    * */
    public HashMap<String, Object> getWorkInfo() {
        HashMap<String,Object> workInfo = new HashMap<>();
//        会员总数量
        Integer memberNum = memberService.selectAllMembers().size();
//        问卷总数量
        Integer questionNum =   questionService.getReplyListByType(1).size()+
                                questionService.getReplyListByType(2).size()+
                                questionService.getReplyListByType(3).size();
//        预约总人数
        Integer orderNum = orderServcie.selectAllOrders().size();

//        预约未执行
        Integer orderUnNum = orderMapper.selectByStu("未到诊").size();
//        已执行
        Integer orderHadNum = orderNum - orderUnNum;
//        今日登录
        Long todayAdd = calculateMemberDAU(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24), new Date());
//        干预方案总数
        Integer adviceNum = adviceService.getAlladvice().size();
        //        TODO 统计日活

//        添加到返回结果
        workInfo.put("memberNum",memberNum);
        workInfo.put("questionNum",questionNum);
        workInfo.put("orderNum",orderNum);
        workInfo.put("adviceNum",adviceNum);
        workInfo.put("orderUnNum",orderUnNum);
        workInfo.put("orderHadNum",orderHadNum);
        workInfo.put("todayAdd",todayAdd);
        return workInfo;
    }
}
