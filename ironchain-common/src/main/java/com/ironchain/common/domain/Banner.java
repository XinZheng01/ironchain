package com.ironchain.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.ironchain.common.base.DataModel;
import com.ironchain.common.domain.Constants.DateConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 轮播图
 * @author zheng xin
 */
@Setter
@Getter
@Entity
@Table(name="banner")
public class Banner extends DataModel {

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_WEB = 1;
	public static final int TYPE_APP = 2;
	
	/** 排序值*/
	@Column(name="sort_id")
	private int sortId = 0;
	
	/** 广告图片标题*/
	@Column(name="title")
	private String title;
	
	/** 图片链接*/
	@Column(name="url")
	private String url;
	
	/** 图片路径*/
	@Column(name="picture_path")
	private String picturePath;
	
	/** 上架时间*/
	@Column(name="show_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern=DateConstants.YYYYMMDDHHMM)
	private Date showTime;
	
	/** 下架时间*/
	@Column(name="un_show_time")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern=DateConstants.YYYYMMDDHHMM)
	private Date unShowTime;
	
	/** 类型 1 web 2 app*/
	@Column(name="type")
	private int type = TYPE_APP;
	
	public String getTypeStr(){
		switch (this.type) {
		case 1:
			return "WEB";
		case 2:
			return "APP";
		}
		return null;
	}
	
}
