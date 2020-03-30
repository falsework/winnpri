package com.winn.aliyun.service.impl;

import com.winn.aliyun.service.DemoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author
 * @description 测试实现类
 * @date 2020/3/30
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService{

	private static Logger log = LogManager.getLogger(DemoServiceImpl.class);

	@Override
	public void testMethod() throws Exception {
		log.info("success!!!!!!");
	}
}
