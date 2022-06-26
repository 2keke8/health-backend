package com.zky.health.constant;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @Description:
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.constant
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 11:10
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
public class MyConstant {
    public static final String LOGIN_TOKEN = "login_tokin";

    private static final String PREFIX_UV = "memberuv";
    private static final String PREFIX_DAU = "memberdau";

    private static final String SPLIT = ":";

    // 单日uv
    public static String getUVKey(String date) {
        return PREFIX_UV + SPLIT + date;
    }

    // 区间UV
    public static String getUVKey(String startDate, String endData) {
        return PREFIX_UV + SPLIT + startDate + SPLIT + endData;
    }

    // 单日DAU
    public static String getDAUKey(String date) {
        //memberdau:20221212
        //memberdau:20221213
        return PREFIX_DAU + SPLIT + date;
    }

    // 区间DAU
    public static String getDAUKey(String startDate, String endDate) {
        return PREFIX_DAU + SPLIT + startDate + SPLIT + endDate;
    }

    //解析成JSON字符串
    public static String getJSONString(int code, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        return jsonObject.toJSONString();
    }
}
