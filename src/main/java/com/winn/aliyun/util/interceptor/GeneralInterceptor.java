package com.winn.aliyun.util.interceptor;

import com.winn.aliyun.util.Constants;
import com.winn.aliyun.util.Tools;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeneralInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LogManager.getLogger(GeneralInterceptor.class);

    /**
     * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制等处理；
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        request.setAttribute("requestStartTime", start);
        //执行方法前为当前线程添加标记
        ThreadContext.put(Constants.TRACE_ID, Tools.getRandomUUID12());
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView；
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        Long startTime = (Long) request.getAttribute("requestStartTime");
        long excuteTime = System.currentTimeMillis() - startTime;
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            log.info(
                    "action method " + hm.getBean().getClass().getName() + "." + hm.getMethod().getName() + " end cost:" + excuteTime + "ms");
        } else {
            log.info("action method " + handler + " end cost:" + excuteTime + "ms");
        }
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面），可以根据ex是否为null判断是否发生了异常，进行日志记录；
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //请求完成删除当前线程的标记
        ThreadContext.clearMap();
    }
}
