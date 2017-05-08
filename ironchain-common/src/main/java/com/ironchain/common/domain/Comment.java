package com.ironchain.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 评论
 * @author zheng xin
 *
 */
@Getter
@Setter
@Entity
@Table(name="comment")
public class Comment extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_INFORMATION = 1;
	public static final int TYPE_CIRCLE = 2;
	
	/** 类型 1资讯 2圈子*/
	@Column(name="type")
	private int type;
	
	/** 目标id*/
	@Column(name="target_id")
	private Long targetId;
	
	/** 评论人*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator")
	private Member creator;
	
	/** 被评论人*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="becomment")
	private Member becomment;
	
	/** 评论内容*/
	@Column(name="content")
	private String content;
	
	/** 父评论*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent")
	private Comment parent;
	
	/** 创建时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
}
