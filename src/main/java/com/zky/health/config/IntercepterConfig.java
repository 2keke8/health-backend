package com.zky.health.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：rc
 * @date ：Created in 2022/6/25 14:53
 * @description：
 */

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns拦截的路径
        String[] addPathPatterns1 = {
                "/api/**"
        };
        //excludePathPatterns排除的路径
        String[] excludePathPatterns1 = {
                "/api/login","/api/system/**"
        };
        //创建用户拦截器对象并指定其拦截的路径和排除的路径
        registry.addInterceptor(new UserIntercepter()).addPathPatterns(addPathPatterns1).excludePathPatterns(excludePathPatterns1);

        //addPathPatterns拦截的路径
        String[] addPathPatterns2 = {
                "/api/system/**"
        };
        //excludePathPatterns排除的路径
        String[] excludePathPatterns2 = {
                "/api/login",
        };
        //创建用户拦截器对象并指定其拦截的路径和排除的路径
        registry.addInterceptor(new QuestionIntercepter()).addPathPatterns(addPathPatterns2).excludePathPatterns(excludePathPatterns2);

    }
}
