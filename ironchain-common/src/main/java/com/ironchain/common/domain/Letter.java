package com.ironchain.common.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 站内信
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="letter")
public class Letter extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	/** 用户*/
	@Column(name="user_id")
	private Long userId;
	
	/** 标题*/
	@Column(name="title")
	private String title;
	
	/** 创建时间*/
	@Column(name="create_time")
	private Date createTime;
	
	/** 状态*/
	@Column(name="status")
	private Integer status;
	
	/** 类型*/
	@Column(name="type")
	private Integer type;
	
	/** 链接*/
	@Column(name="url")
	private String url;
	
	/** 属性*/
	@Column(name="attr")
	private String attr;
	
	/** 内容*/
	@Column(name="content")
	private String content;
	
}
