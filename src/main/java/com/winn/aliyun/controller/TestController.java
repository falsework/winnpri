package com.winn.aliyun.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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


}
