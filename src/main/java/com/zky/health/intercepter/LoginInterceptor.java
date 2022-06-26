//package com.zky.health.intercepter;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
//public class LoginInterceptor implements HandlerInterceptor {
//
//    // 首先会执行的方法
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//
//        boolean flag = false;
//        // 从session中获取对象
//        SecurityAccountLoginModel model = (SecurityAccountLoginModel) request.getSession().getAttribute("user");
//
//            if (model == null) {
//                response.setCharacterEncoding("UTF-8");
//                response.setContentType("application/json; charset=utf-8");
//                PrintWriter out = null;
//                try {
//                    JSONObject res = new JSONObject();
//                res.put("code", "error");
//                    res.put("message", "用户未登录！");
//                    out = response.getWriter();
//                    out.append(res.toString());
//                    return false;
//            } catch (Exception e) {
//                response.sendError(500);
//                return false;
//            }
//            finally {
//                if (out != null) {
//                    out.close();
//                }
//            }
//        } else {
//            UserContext.setUserSession(model);
//            return true;
//        }
//    }
//
//    // 返回ModelAndView之前执行的方法，面向切面编程中的体现，已经进入了controller
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    // 执行Handle完成之后执行的方法
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception exceptio) throws Exception {
//        UserContext.removeUserSession();
//    }
//
//}
