package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 商品规格值
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_product_spec_value")
public class ShopProductSpecValue extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 商品规格*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="spec_id")
	private ShopProductSpec spec;
	
	/** 规格值*/
	@Column(name="value")
	private String value;
	
	public interface SpecValueVO{
		Long getId();
		
		String getValue();
		
		@Value("#{target.spec.id}")
		Long getSpecId();
	}
	
}
