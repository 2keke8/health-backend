package com.zky.health.config;

import com.zky.health.intercepter.QuestionIntercepter;
import com.zky.health.intercepter.UserIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    @Autowired
    UserIntercepter userIntercepter;

    @Autowired
    QuestionIntercepter questionIntercepter;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        //addPathPatterns拦截的路径
        String[] addPathPatterns1 = {
                "/api/**"
        };
        //excludePathPatterns排除的路径
        String[] excludePathPatterns1 = {
                //放行登录界面、系统管理、swagger界面
                "/api/login","/api/system/**","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"
        };
        //创建用户拦截器对象并指定其拦截的路径和排除的路径
        registry.addInterceptor(userIntercepter).addPathPatterns(addPathPatterns1).excludePathPatterns(excludePathPatterns1);


        //addPathPatterns拦截的路径
        String[] addPathPatterns2 = {
                "/api/system/**"
        };
        //excludePathPatterns排除的路径
        String[] excludePathPatterns2 = {
                "/api/login","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"
        };
        //创建用户拦截器对象并指定其拦截的路径和排除的路径
        registry.addInterceptor(questionIntercepter).addPathPatterns(addPathPatterns2).excludePathPatterns(excludePathPatterns2);

    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }

}
