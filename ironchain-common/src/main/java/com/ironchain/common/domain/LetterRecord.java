package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="letter_record")
public class LetterRecord extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	public static final int STATUS_UNREAD = 0;
	public static final int STATUS_READ = 1;
	
	/** 用户*/
	@Column(name="user_id")
	private Long userId;
	
	/** 发送记录*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="letter_id")
	private Letter letter;
	
	/** 状态*/
	@Column(name="status")
	private int status = STATUS_UNREAD;
	
}
