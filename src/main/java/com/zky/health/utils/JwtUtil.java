package com.zky.health.utils;

import io.jsonwebtoken.*;


import java.util.Date;
import java.util.UUID;

/**
 * @Description: 生成JWT工具类
 * @BelongsProject: health
 * @BelongsPackage: com.zky.health.utils
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-06-24 10:26
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
public class JwtUtil {

    //设置时间为一分钟
    private static long time = 1000*60;
    private static String signature = "zhaokeyu007.";

    public static String createToken(String username,int role,String subject){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("username", username)
                .claim("role", role)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;
    }

    public static boolean checkToken(String token){
        if(token == null){
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
