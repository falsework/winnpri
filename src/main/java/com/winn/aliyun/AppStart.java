package com.winn.aliyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
        //log4j2.component.properties配置文件添加属性isThreadContextMapInheritable=true以使子线程能够继承线程上下文映射，默认子线程不可继承。
        //官方文档：http://logging.apache.org/log4j/2.x/manual/thread-context.html
        System.setProperty("isThreadContextMapInheritable", "true");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppStart.class, args);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
