package com.zky.health.controller.advice;

import com.zky.health.constant.MyConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * @Description: 异常通知类
 * @BelongsProject: community
 * @BelongsPackage: com.zky.controller.advice
 * @Author: KeYu-Zhao
 * @CreateTime: 2022-02-15 17:34
 * @Email: 2540560264@qq.com
 * @Version: 1.0
 */
//annotations = Controller.classs的作用是去扫描被@Controller注解的类，如果不加默认是扫描全部的类
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {

    /**
     *  只要项目中出现异常，就会调用这个方法
     * @param e 接受到异常
     * @param request 利用request和response方便对异常的后后续处理
     * @param response
     * @throws IOException
     */
    @ExceptionHandler({Exception.class})//annotations = Controller.classs的作用是去扫描被@Controller注解的类，如果不加默认是扫描全部的类
    public void handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //得到请求方式
        String xRequestedWith = request.getHeader("x-requested-with");
        //如果是异步请求，则返回统一处理数据给前端
        if ("XMLHttpRequest".equals(xRequestedWith)) {
            response.setContentType("application/plain;charset=utf-8");//普通文本，浏览器会自动转换为json格式
            PrintWriter writer = response.getWriter();
            writer.write(MyConstant.getJSONString(1, "服务器异常!"));
        } else {
            //如果不是，则直接返回错误页面
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
