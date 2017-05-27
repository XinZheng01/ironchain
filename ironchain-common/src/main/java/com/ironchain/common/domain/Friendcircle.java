package com.ironchain.common.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 朋友圈
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="friendcircle")
public class Friendcircle extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	/** 内容*/
	@Column(name="content")
	private String content;
	
	/** 发送人*/
	@Column(name="member_id")
	private Long memberId;
	
	/** 发送时间*/
	@Column(name="send_time")
	private Date sendTime;
	
}
