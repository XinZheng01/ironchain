package com.ironchain.intfc.web.advice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ironchain.common.domain.R;
import com.ironchain.common.exception.ServiceException;

/**
 * 全局异常处理
 * 
 * @author zheng xin
 *
 */
@ControllerAdvice
public class ExceptionAdvice {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(ConcurrencyFailureException.class)
    @ResponseBody
    public R processConcurencyError(ConcurrencyFailureException ex, HttpServletRequest request, HttpServletResponse response, Object handler) {
		LOGGER.error("数据库锁", ex);
		return R.error(R.SC_CONCURENCY, "服务器异常");
    }
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public R processServiceError(ServiceException ex, HttpServletRequest request, HttpServletResponse response){
		LOGGER.error(ex.getMessage(), ex);
		return R.error(ex.getSc(), ex.getMessage());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public R processsIllegalArgumentError(IllegalArgumentException ex){
		LOGGER.error(ex.getMessage(), ex);
		return R.error(R.SC_PARAMERROR, ex.getMessage());
	}
	
	@ExceptionHandler(BindException.class)
    @ResponseBody
    public R processValidationError(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder buff = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
        	buff.append(fieldError.getDefaultMessage()).append(";");
		}
        buff.setLength(buff.length() - 1);
        return R.error(R.SC_PARAMERROR, buff.toString());
    }
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public R processMethodNotSupportedError(HttpRequestMethodNotSupportedException ex){
		LOGGER.error(ex.getMessage(), ex);
		return R.error(R.SC_NOFOUND, "非法请求");
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public R processError(Exception ex){
		LOGGER.error(ex.getMessage(), ex);
		return R.error(R.SC_ERROR, ex.getMessage());
	}
	
}
