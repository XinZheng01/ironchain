package com.ironchain.intfc.web.advice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.ironchain.common.kits.Base64;
import com.ironchain.common.kits.DigestKit;
import com.ironchain.intfc.annotation.IgnoreApiSecurity;

/**
 * APP 接口请求解密
 * @author Administrator
 *
 */
@ControllerAdvice
public class RequestDecryptAdvice implements RequestBodyAdvice{
	
private static Logger logger = LoggerFactory.getLogger(RequestDecryptAdvice.class);
	
	private byte[] key = "3AF6F179FC423C8B".getBytes(Charset.forName("UTF-8"));
	
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		IgnoreApiSecurity ignoreSecurity = methodParameter.getMethodAnnotation(IgnoreApiSecurity.class);
		return ignoreSecurity == null || !ignoreSecurity.ignoreRequest();
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
	
}
