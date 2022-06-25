package com.zky.health.config;

import com.zky.health.intercepter.DataInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {


    @Autowired
    DataInterceptor dataInterceptor;

//   @Override
//    protected void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOriginPatterns("*")
//                .allowCredentials(true).allowedMethods("*").maxAge(3600);
//    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(dataInterceptor)
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");

    }
}
