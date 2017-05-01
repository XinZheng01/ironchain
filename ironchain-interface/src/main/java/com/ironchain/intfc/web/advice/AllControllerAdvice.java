package com.ironchain.intfc.web.advice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironchain.common.kits.Base64;
import com.ironchain.common.kits.DigestKit;
import com.ironchain.intfc.annotation.IgnoreSecurity;

@ControllerAdvice
public class AllControllerAdvice implements RequestBodyAdvice, ResponseBodyAdvice<Object>{
	
	private static Logger logger = LoggerFactory.getLogger(AllControllerAdvice.class);
	
	private byte[] key = "3AF6F179FC423C8B".getBytes(Charset.forName("UTF-8"));
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		IgnoreSecurity ignoreSecurity = methodParameter.getMethodAnnotation(IgnoreSecurity.class);
		return ignoreSecurity == null;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}
	
	/**
	 * request aes解密
	 */
	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		return new HttpInputMessage() {
			@Override
			public HttpHeaders getHeaders() {
				return inputMessage.getHeaders();
			}
			
			@Override
			public InputStream getBody() throws IOException {
				InputStream in = inputMessage.getBody();
				byte[] b = new byte[2048];  
			    int len = 0;
			    ByteArrayOutputStream bout = new ByteArrayOutputStream(2048);
			    while ((len = in.read(b)) > 0)
			    	bout.write(b, 0, len);
			    
			    try{
					return new ByteArrayInputStream(DigestKit.aesDecrypt(
							Base64.getDecoder().decode(bout.toByteArray()), key));
			    }catch (Exception e) {
			    	logger.error("解密请求参数异常", e);
				}
			    return null;
			}
		};
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		IgnoreSecurity ignoreSecurity = returnType.getMethodAnnotation(IgnoreSecurity.class);
		return ignoreSecurity == null;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		try {
			String result = objectMapper.writeValueAsString(body);
			logger.debug("加密前数据：{}", result);
			result =  Base64.getEncoder().encodeToString(DigestKit.aesEncrypt(result.getBytes(Charset.forName("UTF-8")), key));
			logger.debug("加密后数据：{}", result);
			return result;
		} catch (Exception e) {
			logger.error("加密响应参数异常", e);
		}
		return null;
	}

}
