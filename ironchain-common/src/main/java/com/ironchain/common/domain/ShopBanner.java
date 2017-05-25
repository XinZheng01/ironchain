package com.ironchain.common.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_banner")
public class ShopBanner extends DataModel {

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_WEB = 1;
	public static final int TYPE_APP = 2;
	
	/** 标题*/
	@Column(name="title")
	private String title;
	
	/** 图片*/
	@Column(name="picture_path")
	private String picturePath;
	
	/** 链接*/
	@Column(name="url")
	private String url;
	
	/** 类型*/
	@Column(name="type")
	private Integer type = TYPE_APP;
	
	/** 排序值*/
	@Column(name="sort_id")
	private Integer sortId = 0;
	
	/** 上架时间*/
	@Column(name="show_time")
	private Date showTime;
	
	/** 下架时间*/
	@Column(name="un_show_time")
	private Date unShowTime;
	
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
