package com.ironchain.intfc.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.ironchain.common.sms.SmsService;
import com.ironchain.common.sms.SmsServiceImpl;
import com.ironchain.common.upload.FileSystemUploadService;
import com.ironchain.common.upload.UploadService;
import com.ironchain.intfc.web.filter.DecryptFilter;
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
	
	@Autowired
	private DecryptFilter decryptFilter;
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
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorizationInterceptor).addPathPatterns("/api/**");
	}
	
	@Bean
	public FilterRegistrationBean filterRegistration() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(decryptFilter);
	    registration.addUrlPatterns("/api/*");//TODO 如果有jsp页面需要添加过滤
	    registration.setName("decryptFilter");
	    registration.setOrder(1);
	    return registration;
	}
	
	//添加支持跨域的url
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//			registry.addMapping("/api/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
	}

	@Bean
	public UploadService getUploadService(ApiProperties apiProperties, ServletContext servletContext){
		return new FileSystemUploadService(apiProperties.getUploadRootPath(),
				apiProperties.getUploadBaseUrl(), servletContext);
	}
	
	@Bean
	public SmsService getSmsService(){
		return new SmsServiceImpl();
	}
}
