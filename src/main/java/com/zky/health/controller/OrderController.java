package com.zky.health.controller;

import com.zky.health.constant.MyConstant;
import com.zky.health.entity.Member;
import com.zky.health.entity.Order;
import com.zky.health.entity.Ordersetting;
import com.zky.health.entity.Result;
import com.zky.health.service.MemberService;
import com.zky.health.service.OrderServcie;
import com.zky.health.service.OrderSettingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @description：预约管理
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 16:28
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */

@CrossOrigin
@RestController
@Api(tags = "预约相关接口")//swagger 标注这是一个控制器类
public class OrderController {

    @Autowired
    OrderServcie orderServcie;

    @Autowired
    MemberService memberService;

    @Autowired
    OrderSettingService orderSettingService;

    /**
     * @description：查询所有预约列表
     * @return：包含List订单列表
     */
    @GetMapping("/api/queryorders")
    public Result selectAllOrders(){

        Result result;

        List<Order> orders = orderServcie.selectAllOrders();



        ArrayList<HashMap<Object, Object>> orderList = new ArrayList();

        //得到每个预约的用户
        for (Order order : orders) {

            //存放返回结果
            HashMap<Object, Object> resMap = new HashMap<>();

            Integer memberId = order.getMemberId();
            Member member = memberService.selectByPrimaryKey(memberId);
            String time = new SimpleDateFormat(MyConstant.TIME_PATTERN).format(order.getOrderdate());

            if(member == null) continue;

            resMap.put("date", time);
            resMap.put("id", order.getId());
            resMap.put("membername", member.getName());
            resMap.put("phone", member.getPhonenumber());
            resMap.put("ordertype", order.getOrdertype());
            resMap.put("orderstatus", order.getOrderstatus());

            orderList.add(resMap);

        }

        result = Result.success();
        result.setMessage("查询预约列表成功");
        result.setData(orderList);

        return result;

    }

    @GetMapping("/api/orderaffirm/{id}")
    public Result orderAffirm(@PathVariable("id") Integer id){

        Result result;

        Order order = orderServcie.selectByPrimaryKey(id);

        int i = orderServcie.affirmOrder(id);

        if(i == -1){
            result = Result.error();
            result.setMessage("已经预约过了~");
            return result;
        }

        if(i > 0){
            result = Result.success();
            result.setMessage("确认预约成功~");
            return result;
        }

        //更新预约设置数据，将剩余人数-1
        Ordersetting ordersetting = orderSettingService.queryOrderSettingByDate(order.getOrderdate());
        ordersetting.setReservations(ordersetting.getReservations()-1);
        orderSettingService.updateOrderSetting(ordersetting);

        result = Result.error();
        result.setMessage("预约失败！请联系管理员");

        return result;
    }

    /**
     * @decription；取消预约
     * @param id
     * @return
     */
    @GetMapping("/api/cancelorder/{id}")
    public Result cancelOrder(@PathVariable("id") Integer id){

        Result result;

        Order order = orderServcie.selectByPrimaryKey(id);

        int i = orderServcie.cancelOrder(order);

        if(i == -1){
            result = Result.error();
            result.setMessage("用户还没有预约哦~请先预约");
        }

        if(i < 0){
            result = Result.error();
            result.setMessage("取消预约失败");
        }

        //更新预约设置数据，将剩余人数+1
        Ordersetting ordersetting = orderSettingService.queryOrderSettingByDate(order.getOrderdate());
        ordersetting.setReservations(ordersetting.getReservations()+1);
        orderSettingService.updateOrderSetting(ordersetting);

        result = Result.success();
        result.setMessage("用户取消预约成功~");

        return result;

    }
    /*
    * 上传预约表格
    * */
    @PostMapping(value = "/api/importExcel")
    public Result ImportExcel(MultipartFile orderfiles) {
        Result result;
        if (orderfiles.isEmpty()) {
            result = Result.error();
            result.setMessage("上传表格失败！");
        } else {

            String fileName = orderfiles.getOriginalFilename();
            if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
                result = Result.error();
                result.setMessage("文件格式不正确！");

            }else {
                boolean OK = orderServcie.insertExcel(fileName, orderfiles);
                if (OK) {
                    result = Result.success();
                    result.setMessage("上传成功！");
                } else {
                    result = Result.error();
                    result.setMessage("发生服务器内部错误，上传失败!");
                }
            }
        }
        return  result;
    }

    /*
    * 下载表格模板
    * */
    @PostMapping(value = "/api/downloadModel")
    public Result downloadModel(){
        Result result;
        File model =  orderServcie.downloadModel();
        if(ObjectUtils.isEmpty(model)){
            result = Result.error();
            result.setMessage("出现未知错误！模板下载失败");
        }else{
            result = Result.success();
            result.setData(model);
            result.setMessage("获取模板成功！！！");
        }

        return  result;
    }


    /**
     * @description：插入预约设置
     * @param ordersetting
     * @return
     */
    @PostMapping("/api/addordersetting")
    public Result addOrderSetting(@RequestBody Ordersetting ordersetting){

        Result result;

        int i = orderSettingService.insertOrderSetting(ordersetting);

        if(i <= 0){
            result = Result.error();
            result.setMessage("插入失败，请联系管理员！");
            return result;
        }

        result = Result.success();
        result.setMessage("插入成功~");

        return result;
    }

    /**
     * @description：查询某个日期的预约人数以及可预约人数
     * @param date
     * @return
     */
    @GetMapping("/api/queryordersettingbydate")
    public Result queryOrderSettingByDate(Date date){

        Result result;

        if(date == null){
            result = Result.error();
            result.setMessage("请选择日期！");
            return result;
        }

        Ordersetting ordersetting = orderSettingService.queryOrderSettingByDate(date);

        result = Result.success();
        result.setMessage("查询预约设置成功");
        result.setData(ordersetting);

        return result;
    }

}
