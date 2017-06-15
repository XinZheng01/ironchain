package com.ironchain.admin.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.ironchain.common.upload.FileSystemUploadService;
import com.ironchain.common.upload.UploadService;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;

/**
 * Mvc 配置
 * @author Administrator
 *
 */
@Configuration
public class MvcConfig extends SpringDataWebConfiguration {
	
	/**
	 * 添加不经过controller的view
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/icon").setViewName("icon");
		registry.addViewController("/uploadTest").setViewName("upload_form");
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
	
	@Bean
	public UploadService getUploadService(AdminProperties adminProperties, ServletContext servletContext){
		return new FileSystemUploadService(adminProperties.getUploadRootPath(),
				adminProperties.getUploadBaseUrl(), servletContext);
	}
	
	//添加支持跨域的url
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/api/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
	}
	
	@Bean
	public JPushClient getJPushClient(AdminProperties adminProperties, @Value("spring.profiles.active") String active){
		ClientConfig config = ClientConfig.getInstance();
		config.setMaxRetryTimes(5);//最大重试次数
		config.setConnectionTimeout(10 * 1000);//连接超时时间
		config.setApnsProduction("product".equals(active));//环境
		
		return new JPushClient(adminProperties.getJpushMasterSecret(),
				adminProperties.getJpushAppKey(),
				null, config);
	}
}
