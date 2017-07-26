package com.ironchain.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 购物车商品项
 * @author Administrator
 *
 */
@Getter
@Setter
public class ShopCartItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 商品id*/
	private Long productId;
	
	/** 商品名称*/
	private String productTitle;
	
	/** skuid*/
	private Long skuId;
	
	/** sku名称*/
	private String skuTitle;
	
	/** 购买数量*/
	private int num;
	
	/** 价格*/
	private BigDecimal price;
	
	/** 商品图片*/
	private String img;
	
//	/** 是否下架*/
//	private boolean unShow;
//	
//	/** 是否库存不足*/
//	private boolean outStock;
}
