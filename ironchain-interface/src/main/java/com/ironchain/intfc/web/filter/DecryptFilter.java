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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ironchain.common.kits.DigestKit;
import com.ironchain.common.kits.JsonKit;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;
import com.ironchain.intfc.web.advice.ApiAnnotationHandler;

/**
 * 请求参数解密过滤器
 * @author zheng xin
 *
 */
@Component
public class DecryptFilter implements Filter {

	@Autowired
	private ApiAnnotationHandler apiAnnotationHandler;
	
	private String aesKey;
	
	private boolean apiDigest;
	
	public DecryptFilter(@Value("${site.aes-key}") String aesKey,
			@Value("${site.api-digest}") String apiDigest){
		this.aesKey = aesKey;
		this.apiDigest = Boolean.valueOf(apiDigest);
	}
	
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String digesBody = request.getParameter("digesBody");
		Map<String, Object> map = Collections.emptyMap();
		
		if(digesBody != null){
			//判断此url是否需要加密
			IgnoreApiSecurity ignoreSecurity = apiAnnotationHandler.getMethodAnnotation(((HttpServletRequest)request).getRequestURI());
			if(apiDigest && (ignoreSecurity == null || !ignoreSecurity.ignoreRequest()))
				digesBody = DigestKit.aesDecrypt(digesBody, aesKey);	
			
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
