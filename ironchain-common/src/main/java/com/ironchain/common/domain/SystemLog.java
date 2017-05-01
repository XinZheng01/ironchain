package com.ironchain.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统日志
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name = "system_log")
public class SystemLog extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	//用户名
	@Column(name="username")
	private String username;
	
	//用户操作
	@Column(name="operation")
	private String operation;
	
	//请求方法
	@Column(name="method")
	private String method;
	
	//请求参数
	@Column(name="params")
	private String params;
	
	//IP地址
	@Column(name="ip")
	private String ip;
	
	//创建时间
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
}
