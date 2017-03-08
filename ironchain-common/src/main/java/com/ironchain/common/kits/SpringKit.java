package com.ironchain.common.kits;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * spring 工具类
 * 用于获取bean 和 ApplicationContext
 * @author Administrator
 *
 */
public class SpringKit implements ApplicationContextAware{
	
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringKit.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		Assert.notNull(applicationContext, "ApplicationContext uninitialized, please add @Import(SpringKit.class) to the Config");
		return applicationContext;
	}
	
	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
	}
	
	public static <T> T getBean(Class<T> clazz){
		return getApplicationContext().getBean(clazz);
	}
	
	public static <T> T getBean(String name, Class<T> clazz){
		return getApplicationContext().getBean(name, clazz);
	}
}
