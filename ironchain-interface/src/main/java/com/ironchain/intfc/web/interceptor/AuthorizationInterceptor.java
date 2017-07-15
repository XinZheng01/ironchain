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
import com.ironchain.common.domain.R;
import com.ironchain.common.exception.ServiceException;
import com.ironchain.intfc.annotation.IgnoreAuth;
import com.ironchain.intfc.config.ApiProperties;

/**
 * Token过滤
 * @author zheng xin
 *
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	private boolean isApiAuth;
	
	public AuthorizationInterceptor(ApiProperties apiProperties){
		this.isApiAuth = apiProperties.isApiAuth();
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!isApiAuth)
			return true;
		
		IgnoreAuth annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        //如果有@IgnoreAuth注解，则不验证token
        if(annotation != null)
            return true;
        
        String userIdStr = request.getParameter("userId");
        String token = request.getParameter("token");
        if(userIdStr == null || token == null || !token.equals(redisTemplate.opsForValue()
        		.get(CacheConstants.LOGIN_TOKEN.getPrefix() + userIdStr))){
        	logger.error("非法请求：未登录，userId：{} token：{}", userIdStr, token);
        	throw new ServiceException(R.SC_NOLOGIN, "非法请求：未登录");
        }
        return true;
	}
}
