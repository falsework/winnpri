package com.winn.aliyun.controller;

import com.winn.aliyun.service.DemoService;
import com.winn.aliyun.util.AppContext;
import com.winn.aliyun.util.MsgCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Scope("singleton") //只实例化一个bean对象（即每次请求都使用同一个bean对象），默认是singleton
public class TestController {
    private static Logger log = LogManager.getLogger(TestController.class);

    @RequestMapping("/index")
    @ResponseBody
    public String index() throws Exception {
        return "阿里云项目启动成功";
    }

    @RequestMapping("/hello")
    public String hello() throws Exception {
        log.info("info..............");
        log.debug("debug..............");
        log.error("error..............");
        log.warn("warn..............");
        return "jsp/hello";
    }

    /**
     * 功能描述:  测试bean的获取
     *
     *  controller中的void方法一定要声明HttpServletResponse类型的入参
     *  当没有使用@ResponseBody注解，而且请求参数中没有HttpServletResponse时，容器把其当做视图来解析，
     *  没有返回值时，会把requestMapping中的value当做视图名称
     *
     *
     *
     *  方式一：通过声明HttpServletResponse类型的方法入参，来使用HttpServletResponse对象。
     *        注意：在Controller中，@RequestMapping注解的方法，在调用这个方法时候，
     *             如果有定义HttpServletResponse类型的入参，Spring MVC框架会自动传入一个HttpServletResponse对象作为方法参数；
     *             如果有定义HttpServletRequest类型的入参，Spring MVC框架会自动传入一个HttpServletRequest对象作为方法参数。

     * 方式二：void方法不定义HttpServletResponse类型的入参，HttpServletResponse对象通过RequestContextHolder上下文获取
     *             注意：这种方式是不可行的，void方法不定义HttpServletResponse类型的入参，
     *                  Spring MVC会认为@RequestMapping注解中指定的路径就是要返回的视图name，

     *
     */
    @RequestMapping("/testMethod1")
    public void testMethod1() throws Exception {
        DemoService obj = AppContext.getBean("demoService");
        log.info(obj);
        obj.testMethod();

    }


}
