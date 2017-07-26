package com.ironchain.common.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 商品SKU
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_product_sku")
public class ShopProductSku extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 商品*/
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_id")
	private ShopProduct product;
	
	/** sku标题*/
	@Column(name="title")
	private String title;
	
	/** 商品规格值*/
	@Column(name="spec_items")
	private String specItems;
	
	/** 商品编号*/
	@Column(name="code")
	private String code;
	
	/** 商品价格*/
	@Column(name="price")
	private BigDecimal price;
	
	/** 商品库存*/
	@Column(name="stock")
	private Integer stock;

	@Override
	public String toString() {
		return "ShopProductSku [specItems=" + specItems + ", code=" + code + ", price=" + price + ", stock=" + stock
				+ "]";
	}
	
}
