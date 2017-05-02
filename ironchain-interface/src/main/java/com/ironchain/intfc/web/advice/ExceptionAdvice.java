package com.ironchain.intfc.web.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ironchain.common.domain.ResponseResult;
import com.ironchain.common.exception.ServiceException;

@ControllerAdvice
public class ExceptionAdvice {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(ConcurrencyFailureException.class)
    @ResponseBody
    public ResponseResult processConcurencyError(ConcurrencyFailureException ex) {
		LOGGER.error("数据库锁", ex);
		return new ResponseResult(ResponseResult.SC_CONCURENCY, "服务器异常", null);
    }
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseResult processServiceError(ServiceException ex){
		LOGGER.error(ex.getMessage(), ex);
		return new ResponseResult(ex.getSc(), ex.getMessage(), null);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ResponseResult processsIllegalArgumentError(IllegalArgumentException ex){
		LOGGER.error(ex.getMessage(), ex);
		return new ResponseResult(ResponseResult.SC_PARAMERROR, ex.getMessage(), null);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseResult processError(Exception ex){
		LOGGER.error(ex.getMessage(), ex);
		return new ResponseResult(ResponseResult.SC_ERROR, ex.getMessage(), null);
	}
	
}
