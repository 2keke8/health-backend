package com.zky.health.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

//
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){

        //配置了Swagger的Docket的Bean实例
//        DocumentationType documentationType = new DocumentationType();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //RequestHandlerSelectors,配置要扫描接口的方式
                //basePackage指定扫描的包
                //any扫描全部的包
                //none()都不扫描
                //withClassAnnotation()扫描类上的注解，参数是注解的反射对象
                //withMethodAnnotation()扫描方法上的注解   多人开发也在这里配置：不同包下的controller
                .apis(RequestHandlerSelectors.basePackage("com.zky.health.controller"))
                //paths()，过滤路径，只让/kuang开头的请求通过
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    //配置swagger信息需要apiInfo类
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("zky","https://www.baidu.com/","2540560264@qq.com");
        return new ApiInfo(
                "web实训的Swagger日记",
                "川师最强小组",
                "v1.0",
                "https://www.baidu.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

    private ApiInfo apiInfo1(){
        //作者信息
        Contact contact = new Contact("zky","https://www.baidu.com/","2540560264@qq.com");
        return new ApiInfo(
                "zky的Swagger日记",
                "玉树临风",
                "v1.0",
                "https://www.baidu.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

    private ApiInfo apiInfo2(){
        //作者信息
        Contact contact = new Contact("zky","https://www.baidu.com/","2540560264@qq.com");
        return new ApiInfo(
                "lpc的Swagger日记",
                "英俊潇洒",
                "v1.0",
                "https://www.baidu.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("zky").apiInfo(apiInfo1());
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("lpc").apiInfo(apiInfo2());
    }

}
