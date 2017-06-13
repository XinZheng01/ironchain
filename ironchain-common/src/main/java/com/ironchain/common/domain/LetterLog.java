package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 站内信日志
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="letter_log")
public class LetterLog extends DataModel {

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_TEXT = 1;//文本内容
	public static final int TYPE_URL = 2;//链接
	public static final int TYPE_DEMAND = 3;//需求
	
	public static final int SEND_TYPE_ALL = 1;//所有用户
	public static final int SEND_TYPE_APPOINT = 2;//指定用户
	
	/** 标题*/
	@Column(name="title")
	private String title;
	
	/** 内容*/
	@Column(name="content")
	private String content;
	
	/** 接收人数*/
	@Column(name="number")
	private Integer number;
	
	/** 指定发送的用户*/
	@Column(name="members")
	private String members;
	
	/** 类型*/
	@Column(name="type")
	private Integer type = TYPE_TEXT;
	
	/** 发送类型*/
	@Column(name="send_type")
	private Integer sendType = SEND_TYPE_ALL;
	
	/** 链接*/
	@Column(name="url")
	private String url;
	
	/** 属性*/
	@Column(name="attr")
	private String attr;
	
}
