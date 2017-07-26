package com.ironchain.intfc.web.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.ironchain.intfc.annotation.IgnoreApiSecurity;

/**
 * 获取所有Controller方法的注解
 * 
 * @see IgnoreApiSecurity
 * @author zheng xin
 *
 */
@Component
public class ApiAnnotationHandler {
	
	private Map<String, IgnoreApiSecurity> urlMap;
	
	@Autowired
	public ApiAnnotationHandler(ServletContext servletContext, RequestMappingHandlerMapping requestMappingHandlerMapping){
		String ctx = servletContext.getContextPath();
		urlMap = new HashMap<>();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : requestMappingHandlerMapping.getHandlerMethods().entrySet()) {
			Set<String> patterns = entry.getKey().getPatternsCondition().getPatterns();
			for (String pattern : patterns) {
				urlMap.put(ctx + pattern, entry.getValue()
						.getMethodAnnotation(IgnoreApiSecurity.class));
			}
		}
	}
	
	public IgnoreApiSecurity getMethodAnnotation(String requestUrl){
		return this.urlMap.get(requestUrl);
	}
}
