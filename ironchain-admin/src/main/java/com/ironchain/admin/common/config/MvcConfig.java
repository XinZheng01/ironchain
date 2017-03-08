package com.ironchain.admin.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Mvc 配置
 * @author Administrator
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	/**
	 * 添加不经过controller的view
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/home").setViewName("home");
	}
	
}
