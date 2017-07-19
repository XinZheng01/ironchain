package com.ironchain.intfc.web.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ironchain.common.kits.DigestKit;
import com.ironchain.common.kits.JsonKit;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;
import com.ironchain.intfc.config.ApiProperties;
import com.ironchain.intfc.web.advice.ApiAnnotationHandler;

/**
 * 请求参数解密过滤器
 * @author zheng xin
 *
 */
@Component
public class DecryptFilter implements Filter {
	
	private Logger LOGGER = LoggerFactory.getLogger(DecryptFilter.class);
	
	@Autowired
	private ApiAnnotationHandler apiAnnotationHandler;
	
	private String aesKey;
	
	private boolean apiDigest;
	
	public DecryptFilter(ApiProperties apiProperties){
		this.aesKey = apiProperties.getAesKey();
		this.apiDigest = apiProperties.isApiDigest();
	}
	
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String digesBody = request.getParameter("digesBody");
		Map<String, Object> map = Collections.emptyMap();
		String uri = ((HttpServletRequest)request).getRequestURI();
		LOGGER.debug(uri);
		LOGGER.debug(digesBody);
		if(digesBody != null){
			//判断此url是否需要加密
			IgnoreApiSecurity ignoreSecurity = apiAnnotationHandler.getMethodAnnotation(uri);
			if(apiDigest && (ignoreSecurity == null || !ignoreSecurity.ignoreRequest())){
				digesBody = DigestKit.aesDecrypt(digesBody, aesKey);
				LOGGER.debug(digesBody);
			}
			map = JsonKit.normal().fromJson(digesBody, HashMap.class);
		}
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper((HttpServletRequest)request, map);
		//放行
		chain.doFilter(requestWrapper, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
	
}
