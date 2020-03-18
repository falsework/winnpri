package com.winn.aliyun.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/index")
    public String index() throws Exception {
        return "阿里云项目启动成功";
    }

}
