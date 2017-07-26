package com.ironchain.common.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 商品
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_product")
public class ShopProduct extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 显示状态 下架*/
	public static final int DISPLAY_UNSHOW = 0;
	/** 显示状态 上架*/
	public static final int DISPLAY_SHOW = 1;
	/** 显示状态 违规*/
	public static final int DISPLAY_ILLEGAL = 10;
	
	/** 商品标题*/
	@Column(name="title")
	private String title;
	
	/** 所属分类*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="class_id")
	private ShopClass shopClass;
	
	/** 商品编号*/
	@Column(name="code")
	private String code;
	
	/** 商品价格*/
	@Column(name="price")
	private BigDecimal price;
	
	/** 商品库存*/
	@Column(name="stock")
	private Integer stock;
	
	/** 商品运费*/
	@Column(name="freight")
	private BigDecimal freight = BigDecimal.ZERO;
	
	/** 状态*/
	@Column(name="status")
	private Integer status = DISPLAY_SHOW;
	
	/** 排序值*/
	@Column(name="sort_id")
	private int sortId = 0;
	
	/** 商品详情*/
	@Lob
	@Column(name="content")
	private String content;
	
	/** 上下架时间*/
	@Column(name="sale_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date saleDate;
	
	/** 商品主图*/
	@Column(name="img")
	private String img;
	
	/** 商品图片列表*/
	@Column(name="img_array")
	private String imgArray;
	
	/** 销售数量*/
	@Column(name="sales")
	private int sales = 0;
	
	/** 评价数*/
	@Column(name="evaluates")
	private int evaluates = 0;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="product")
	private List<ShopProductParam> params = new ArrayList<>(0);
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="product")
	private List<ShopProductSku> skus = new ArrayList<>(0);
	
}
