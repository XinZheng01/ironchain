package com.ironchain.common.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 订单商品表
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_order_product")
public class ShopOrderProduct extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	/** 订单ID*/
	@Column(name="order_id")
	private Long orderId;
	
	/** 商品ID*/
	@Column(name="product_id")
	private Long productId;
	
	/** 商品标题*/
	@Column(name="product_title")
	private String productTitle;
	
	/** 商品图片*/
	@Column(name="product_img")
	private Integer productImg;
	
	/** skuID*/
	@Column(name="sku_id")
	private Long skuId;
	
	/** 商品价格*/
	@Column(name="price")
	private BigDecimal price;
	
	/** 购买数量*/
	@Column(name="num")
	private Integer num;
	
	/** 调整金额*/
	@Column(name="adjust_price")
	private BigDecimal adjustPrice;
	
	/** 商品总价*/
	@Column(name="total_price")
	private BigDecimal totalPrice;
	
}
