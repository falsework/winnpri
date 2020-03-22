package com.winn.aliyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {

    public static void main(String[] args) {
        //log4j日志异步输出
        //或者在启动命令中加入java -jar -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector xxx.jar
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(AppStart.class, args);
    }
}
