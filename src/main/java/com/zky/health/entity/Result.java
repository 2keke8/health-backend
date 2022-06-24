package com.zky.health.entity;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.entity
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-23 10:46
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
import java.io.Serializable;

/**
 * 封装返回结果
 */
public class Result implements Serializable{


    private  String code;
    private boolean flag;//执行结果，true为执行成功 false为执行失败
    private String message;//返回结果信息
    private Object data;//返回数据

    public static Result success(){
        Result res = new Result(true,"成功");
        res.setCode("200");
        return res;
    }

    public static Result error(){
        Result res = new Result(false,"失败");
        res.setCode("401");
        return res;
    }



    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
