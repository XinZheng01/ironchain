package com.ironchain.common.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 商品规格
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_product_spec")
public class ShopProductSpec extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 规格名*/
	@Column(name="name")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="spec")
	private Set<ShopProductSpecValue> specValues = new HashSet<>(0);
}
