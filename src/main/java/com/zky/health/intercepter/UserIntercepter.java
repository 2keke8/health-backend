package com.zky.health.intercepter;

import com.alibaba.excel.util.StringUtils;
import com.zky.health.entity.User;
import com.zky.health.service.UserService;
import com.zky.health.utils.JwtUtil;
import com.zky.health.utils.JwtUtil2;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @author ：rc
 * @date ：Created in 2022/6/25 14:45
 * @description：全局拦截器
 */
@Configuration
public class UserIntercepter implements HandlerInterceptor {


        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String tokenName = "token";
            // 尝试从header中取token
            String token = request.getHeader(tokenName);
            //尝试从参数中取token
            if (StringUtils.isEmpty(token)) {
                token = request.getParameter(tokenName);
            }
            //尝试从cooke
            if (StringUtils.isEmpty(token)) {
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if (Objects.equals(cookie.getName(), tokenName)) {
                        token = cookie.getValue();
                    }
                }
            }
            //如果前端没有携带token返回json数据
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            //解析token
            if ( !JwtUtil.checkToken(token)) {
                throw new Exception( "用户未登录");
            }

            //解析token

            Integer role;
            try {
                Claims claims = JwtUtil2.parseJWT(token);
                role = (Integer) claims.get("role");
            } catch (Exception e) {
                e.printStackTrace();
                throw  new RuntimeException("token非法！-!");
            }
//            鉴权，需权限1或者2
            if(role == 1){
                return true;
            }else if(role == 2){
                return true;
            }else {
                return false;
            }


        }


}
