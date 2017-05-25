package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 资讯
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name="information")
public class Information extends DataModel {

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_ARTICLE = 1;
	public static final int TYPE_AD = 2;
	
	/** 类型 1 文章 2广告*/
	@Column(name="type")
	private int type = 1;
	
	/** 标题*/
	@Column(name="title")
	private String title;
	
	/** 来源*/
	@Column(name="source")
	private String source;
	
	/** 封面图片*/
	@Column(name="picture")
	private String picture;
	
	/** 排序值*/
	@Column(name="order_num")
	private int orderNum = 0;
	
	/** 状态 1已发布 0未发布*/
	@Column(name="status")
	private int status = 1;
	
	/** SEO关键字*/
	@Column(name="keywords")
	private String keywords;
	
	/** SEO描述*/
	@Column(name="description")
	private String description;
	
	/** 内容*/
	@Lob
	@Column(name="content")
	private String content;
	
	public String getTypeStr(){
		switch (this.type) {
		case 1:
			return "文章";
		case 2:
			return "广告";
		}
		return null;
	}
	
	public String getStatusStr(){
		switch (this.status) {
		case 0:
			return "未发布";
		case 1:
			return "已发布";
		}
		return null;
	}
}
