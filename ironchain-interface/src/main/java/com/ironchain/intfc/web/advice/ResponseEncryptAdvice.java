package com.ironchain.intfc.web.advice;

import java.io.OutputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.ironchain.common.kits.Base64;
import com.ironchain.common.kits.DigestKit;
import com.ironchain.common.kits.JsonKit;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;
import com.ironchain.intfc.config.ApiProperties;

/**
 * APP接口响应加密
 * 
 * @author zheng xin
 *
 */
@ControllerAdvice
public class ResponseEncryptAdvice implements ResponseBodyAdvice<Object>{
	
	private static Logger logger = LoggerFactory.getLogger(ResponseEncryptAdvice.class);
	
	private byte[] aesKey;
	
	private boolean apiDigest;
	
	public ResponseEncryptAdvice(ApiProperties apiProperties){
		this.aesKey = apiProperties.getAesKey().getBytes(Charset.forName("UTF-8"));
		this.apiDigest = apiProperties.isApiDigest();
	}
	
	@Autowired
	private ApiAnnotationHandler apiAnnotationHandler;
	
	/**
	 * 是否执行加密
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return apiDigest;
	}

	/**
	 * 数据要输出前处理
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		//判断此url是否需要加密
		IgnoreApiSecurity ignoreSecurity = apiAnnotationHandler.getMethodAnnotation(
				((ServletServerHttpRequest)request).getServletRequest().getRequestURI());
		if(ignoreSecurity != null && ignoreSecurity.ignoreResponse())
			return body;
		
		try {
			String result = JsonKit.normal().toJson(body);
			logger.debug("加密前数据：{}", result);
			OutputStream out = response.getBody();
			out.write(Base64.getEncoder().encode(DigestKit.aesEncrypt(result.getBytes(Charset.forName("UTF-8")), aesKey)));
			out.flush();
		} catch (Exception e) {
			logger.error("加密响应参数异常", e);
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(DigestKit.aesEncrypt("{\"oldPassword\":\"<script></script>\",\"newPassword\":\"123\"}", "3AF6F179FC423C8B"));
		System.out.println(DigestKit.aesDecrypt("RDrTS4ptQgye7JZXaV/Xu2rMgtN0hhxDvglcLUiLLDY=", "3AF6F179FC423C8B"));
	}
}
