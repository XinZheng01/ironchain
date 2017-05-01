package com.ironchain.intfc.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ironchain.common.domain.Constants.CacheConstants;
import com.ironchain.common.domain.ResponseResult;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.intfc.annotation.IgnoreAuth;

/**
 * Token过滤
 * @author Administrator
 *
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		IgnoreAuth annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        }else{
        	logger.warn("handler类型不为HandlerMethod");
            return true;
        }
        //如果有@IgnoreAuth注解，则不验证token
        if(annotation != null)
            return true;
        
        String userIdStr = request.getParameter("userId");
        String token = request.getParameter("token");
        if(userIdStr == null || token == null || !token.equals(redisTemplate.opsForValue()
        		.get(CacheConstants.LOGIN_TOKEN.getPrefix() + userIdStr))){
        	logger.error("非法请求：未登录，userId：{} token：{}", userIdStr, token);
        	throw new ServiceException(ResponseResult.SC_NOLOGIN, "非法请求：未登录");
        }
        return true;
	}
}
