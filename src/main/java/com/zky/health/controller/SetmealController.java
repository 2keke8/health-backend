package com.zky.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

import static com.itheima.constant.MessageConstant.PIC_UPLOAD_FAIL;
import static com.itheima.constant.MessageConstant.PIC_UPLOAD_SUCCESS;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.controller
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-23 10:35
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;


    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }
    //图片上传
    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        //System.out.println("前端获取到的文件名================》"+imgFile.getName());
        //获取上传文件的文件后缀，并使用substring()截取
        String originalFilename = imgFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        //System.out.println("截取到的index==================>"+index );
        String extention = originalFilename.substring(index);
        //System.out.println("截取到的extention==================>"+extention );
        //生成随机文件名，并将截取到的文件后缀组装
        String fileName = UUID.randomUUID().toString() + extention;
        try {
            //将文件上传进七牛服务器
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
            //图片上传到七牛云的同时，将文件名写入redis数据库集合
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, PIC_UPLOAD_FAIL);
        }
        return new Result(true, PIC_UPLOAD_SUCCESS, fileName);
    }

    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        try {
            setmealService.add(setmeal, checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            //新增套餐失败
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        //新增套餐成功
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }
}
