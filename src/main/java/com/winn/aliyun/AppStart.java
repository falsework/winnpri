package com.winn.aliyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时任务注解
@EnableScheduling
//不配置包扫描范围是默认扫描范围是该启动类所在的包以及子包，
//@ComponentScan("com.winn.aliyun")
//加载指定文件，默认只能加载properties文件
@PropertySource("classpath:jdbc.properties")
public class AppStart {

    public static void main(String[] args) {
        //log4j日志异步输出
        //或者在启动命令中加入java -jar -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector xxx.jar
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppStart.class, args);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
