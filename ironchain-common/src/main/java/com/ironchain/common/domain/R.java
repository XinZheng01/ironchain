package com.ironchain.common.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 响应结果集
 * Response Result
 * @author Administrator
 *
 */
@Getter
@Setter
@Accessors(chain=true)
public class R implements Serializable{
	
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
	
	public R(){
	}
	
	/**
	 * 成功
	 * @return
	 */
	public static R ok(){
		R r = new R();
		return r;
	}
	
	/**
	 * 成功
	 * @param data
	 * @return
	 */
	public static R ok(Object data){
		R r = new R();
		r.data = data;
		return r;
	}
	
	/**
	 * 异常
	 * @return
	 */
	public static R error(){
		R r = new R();
		r.sc = SC_ERROR;
		return r;
	}
	
	/**
	 * 异常
	 * @param sc
	 * @param msg
	 * @return
	 */
	public static R error(short sc, String msg){
		R r = new R();
		r.sc = sc;
		r.msg = msg;
		return r;
	}
}
