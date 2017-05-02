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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironchain.common.kits.Base64;
import com.ironchain.common.kits.DigestKit;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;

/**
 * APP接口响应加密
 * @author Administrator
 *
 */
@ControllerAdvice
public class ResponseEncryptAdvice implements  ResponseBodyAdvice<Object>{
	
	private static Logger logger = LoggerFactory.getLogger(ResponseEncryptAdvice.class);
	
	private byte[] key = "3AF6F179FC423C8B".getBytes(Charset.forName("UTF-8"));
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		IgnoreApiSecurity ignoreSecurity = returnType.getMethodAnnotation(IgnoreApiSecurity.class);
		return ignoreSecurity == null || !ignoreSecurity.ignoreResponse();
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		try {
			String result = objectMapper.writeValueAsString(body);
			logger.debug("加密前数据：{}", result);
			OutputStream out = response.getBody();
			out.write(Base64.getEncoder().encode(DigestKit.aesEncrypt(result.getBytes(Charset.forName("UTF-8")), key)));
			out.flush();
		} catch (Exception e) {
			logger.error("加密响应参数异常", e);
		}
		return null;
	}

}
