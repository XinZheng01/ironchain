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
@Table(name="letter")
public class Letter extends DataModel {

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_LETTER = 0;//站内信
	public static final int TYPE_TEXT = 1;//文本内容
	public static final int TYPE_URL = 2;//链接
	public static final int TYPE_DEMAND = 3;//需求
	
	public static final int SEND_TYPE_ALL = 1;//所有用户
	public static final int SEND_TYPE_APPOINT = 2;//指定用户
	
	public static final int IS_SYSTEM_YES = 1;//系统发送
	public static final int IS_SYSTEM_NO = 0;//非系统发送
	
	/** 标题*/
	@Column(name="title")
	private String title;
	
	/** 内容*/
	@Column(name="content")
	private String content;
	
	/** 接收人数*/
	@Column(name="number")
	private long number;
	
	/** 指定发送的用户*/
	@Column(name="members")
	private String members;
	
	/** 类型*/
	@Column(name="type")
	private int type = TYPE_TEXT;
	
	/** 发送类型*/
	@Column(name="send_type")
	private int sendType = SEND_TYPE_ALL;
	
	/** 发送类型*/
	@Column(name="is_system")
	private int is_system = IS_SYSTEM_NO;
	
	/** 链接*/
	@Column(name="url")
	private String url;
	
	/** 属性*/
	@Column(name="attr")
	private String attr;
	
	public String getTypeStr(){
		switch (type) {
		case TYPE_TEXT:
			return "文本内容";
		case TYPE_URL:
			return "链接";
		case TYPE_DEMAND:
			return "加工需求、设备服务 ";
		default:
			return null;
		}
	}
	
	public String getSendTypeStr(){
		switch (sendType) {
		case SEND_TYPE_ALL:
			return "所有用户";
		case SEND_TYPE_APPOINT:
			return "指定用户";
		default:
			return null;
		}
	}
}
