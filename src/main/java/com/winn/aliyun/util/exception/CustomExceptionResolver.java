package com.winn.aliyun.util.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winn.aliyun.util.MsgCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {

    private static Logger log = LogManager.getLogger(CustomExceptionResolver.class);

    // 前端控制器DispatcherServlet在进行HandlerMapping、调用HandlerAdapter执行Handler过程中，如果遇到异常就会执行此方法
    // handler最终要执行的Handler，它的真实身份是HandlerMethod
    // Exception ex就是接收到异常信息
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        //输出异常
        log.info("CustomExceptionResolver.resolveException：" + ex);

        //统一异常处理
        //针对系统自定义的异常CustomException异常，可以直接从异常中获取异常信息，将异常处理在错误页面展示
        CustomException customException = null;
        if (ex instanceof CustomException) {
            customException = ((CustomException) ex);
        } else {
            //针对非自定义异常，重新构造一个自定义异常，"系统繁忙,请稍后再试..."
            customException = new CustomException(MsgCode.SYSTEM_ERROR);
        }
        PrintWriter writer = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            writer = response.getWriter();
            Map<String, String> map = new HashMap<>();
            map.put("code", customException.getCode());
            map.put("msg", customException.getMsg());
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(map);
            writer.write(result);
        } catch (IOException e) {
            log.info(e.getMessage(), e);
            writer.write(MsgCode.SYSTEM_ERROR.toJson());
        }
        return new ModelAndView();
    }
}
