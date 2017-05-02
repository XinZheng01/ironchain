package com.ironchain.intfc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.ironchain.intfc.web.interceptor.AuthorizationInterceptor;

/**
 * Mvc 配置
 * @author Administrator
 *
 */
@Configuration
public class MvcConfig extends SpringDataWebConfiguration {
	
	@Autowired
    private AuthorizationInterceptor authorizationInterceptor;
	/**
	 * 添加不经过controller的view
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/icon").setViewName("icon");
	}
	
	/**
	 * 设置默认分页参数
	 */
	@Override
	public PageableHandlerMethodArgumentResolver pageableResolver() {
		PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver(sortResolver());
		pageHandler.setFallbackPageable(new PageRequest(0, 10));
		pageHandler.setOneIndexedParameters(true);
		return pageHandler;
	}
	
//	@Override
//    public void addFormatters(FormatterRegistry registry) {
//        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
//        registrar.setUseIsoFormat(true);
//        registrar.registerFormatters(registry);
//    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorizationInterceptor).addPathPatterns("/api/**");
	}
}
