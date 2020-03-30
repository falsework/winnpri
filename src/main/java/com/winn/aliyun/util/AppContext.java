package com.winn.aliyun.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author
 * @description 加载容器上下文
 * @date 2020/3/23
 */
@Component
public class AppContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 获取根据名字注册的bean
	 * @param beanName
	 * @param <T>
	 * @return
	 * @throws BeansException
	 */
	public static <T> T getBean(String beanName) throws BeansException {
		return (T) applicationContext.getBean(beanName);
	}

	public static <T> T getbean(Class<T> clz) throws BeansException {
		return applicationContext.getBean(clz);
	}






}
