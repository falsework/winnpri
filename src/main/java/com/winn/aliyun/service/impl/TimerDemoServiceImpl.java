package com.winn.aliyun.service.impl;

import com.winn.aliyun.service.TimerDemoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author
 * @description 定时任务测试类
 * @date 2020/3/23
 */
@Service
public class TimerDemoServiceImpl implements TimerDemoService {

	 private static Logger log= LogManager.getLogger(TimerDemoServiceImpl.class);

	 //项目启动一秒后，每隔5秒执行一次
	@Override
	@Scheduled(initialDelay = 1000, fixedDelay = 60000)
	public void timerDeom() throws Exception {
		log.info("我还没死，还在执行。。。。");
	}
}
