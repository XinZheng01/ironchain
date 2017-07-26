package com.ironchain.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private int type = TYPE_ARTICLE;
	
	/** 标题*/
	@Column(name="title")
	private String title;
	
	/** 来源*/
	@Column(name="source")
	private String source;
	
	/** 封面图片*/
	@Column(name="img")
	private String img;
	
	/** 排序值*/
	@Column(name="sort_id")
	private int sortId = 0;
	
	/** 状态 1已发布 0未发布*/
	@Column(name="status")
	private int status = Constants.DISPLAY_SHOW;
	
	/** SEO关键字*/
	@Column(name="keywords")
	private String keywords;
	
	/** SEO描述*/
	@Column(name="description")
	private String description;
	
	/** 上架时间*/
	@Column(name="show_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date showTime;
	
	/** 下架时间*/
	@Column(name="un_show_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date unShowTime;
	
	/** 内容*/
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name="content")
	private String content;
	
	public String getTypeStr(){
		switch (this.type) {
		case TYPE_ARTICLE:
			return "文章";
		case TYPE_AD:
			return "广告";
		}
		return null;
	}
	
	public String getStatusStr(){
		switch (this.status) {
		case Constants.DISPLAY_UNSHOW:
			return "未发布";
		case Constants.DISPLAY_SHOW:
			return "已发布";
		}
		return null;
	}
}
