package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 商品参数
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_product_param")
public class ShopProductParam extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 商品*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	private ShopProduct product;
	
	/** 参数名*/
	@Column(name="name")
	private String name;
	
	/** 参数值*/
	@Column(name="value")
	private String value;
	
	/** 排序值*/
	@Column(name="sort_id")
	private Integer sortId;
	
}
