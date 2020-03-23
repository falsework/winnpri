package com.winn.aliyun.util.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomExceptionResolver implements HandlerExceptionResolver {

    private static Logger log = LogManager.getLogger(CustomExceptionResolver.class);

    // 前端控制器DispatcherServlet在进行HandlerMapping、调用HandlerAdapter执行Handler过程中，如果遇到异常就会执行此方法
    // handler最终要执行的Handler，它的真实身份是HandlerMethod
    // Exception ex就是接收到异常信息
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler,
            Exception ex) {
        //输出异常
        log.info("CustomExceptionResolver.resolveException：" + ex);




        return null;
    }
}
