package com.ironchain.common.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 成功*/
	public final static short SC_SUCCESS = 200;
	/** 未登录*/
	public final static short SC_NOLOGIN = 4011;
	/** 没权限*/
	public final static short SC_UNAUTHORIZED = 4012;
	/** 未找到资源*/
	public final static short SC_NOFOUND = 404;
	/** 数据库锁*/
	public final static short SC_CONCURENCY = 409;
	/** 服务器异常*/
	public final static short SC_ERROR = 500;
	/** 非法请求*/
	public final static short SC_PARAMERROR = 501;

	/** 响应码*/
	private short sc = SC_SUCCESS;
	
	/** 信息*/
	private String msg = "";
	
	/** 数据*/
	private Object data = "";
	
	public ResponseResult(){
	}
	
	public ResponseResult(short sc, String msg, Object data){
		this.sc = sc;
		if(msg != null)
			this.msg = msg;
		if(data != null)
			this.data = data;
	}
	
}
