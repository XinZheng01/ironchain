package com.ironchain.common.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 商品评价表
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_product_evaluate")
public class ShopProductEvaluate extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	/** 好评*/
	public static final int TYPE_GOOD = 1;
	/** 中评*/
	public static final int TYPE_MIDDLE = 2;
	/** 差评*/
	public static final int TYPE_BAD = 3;
	
	/** 订单ID*/
	@Column(name="order_id")
	private Long orderId;
	
	/** 订单编号*/
	@Column(name="order_no")
	private String orderNo;
	
	/** 订单项ID*/
	@Column(name="order_product_id")
	private Long orderProductId;
	
	/** 商品ID*/
	@Column(name="product_id")
	private Long productId;
	
	/** 商品名称*/
	@Column(name="product_title")
	private String productTitle;
	
	/** 商品价格*/
	@Column(name="product_price")
	private BigDecimal productPrice;
	
	/** 商品图片*/
	@Column(name="product_img")
	private String productImg;
	
	/** 评价内容*/
	@Column(name="content")
	private String content;
	
	/** 评价时间*/
	@Column(name="create_time")
	private Date createTime;
	
	/** 评价图片*/
	@Column(name="img")
	private String img;
	
	/** 解释内容*/
	@Column(name="explain")
	private String explain;
	
	/** 评价人名称*/
	@Column(name="member_name")
	private String memberName;
	
	/** 评价人编号*/
	@Column(name="member_id")
	private Long memberId;
	
	/** 0表示不是 1表示是匿名评价*/
	@Column(name="is_anonymous")
	private int isAnonymous = Constants.JUDGE_NO;
	
	/** 1-5分*/
	@Column(name="scores")
	private Integer scores;
	
	/** 追加评价内容*/
	@Column(name="again_content")
	private String againContent;
	
	/** 追加评价时间*/
	@Column(name="again_create_time")
	private Date againCreateTime;
	
	/** 追加评价图片*/
	@Column(name="again_img")
	private String againImg;
	
	/** 追加解释内容*/
	@Column(name="again_explain")
	private String againExplain;
	
	/** 1好评2中评3差评*/
	@Column(name="type")
	private int type = TYPE_GOOD;
	
}
