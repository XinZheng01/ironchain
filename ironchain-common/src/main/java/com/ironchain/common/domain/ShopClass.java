package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 商品分类
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="shop_class")
public class ShopClass extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 分类名称*/
	@Column(name="name")
	private String name;
	
	/** 上级分类*/
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private ShopClass parent;
	
	/** 排序值*/
	@Column(name="sort_id")
	private Integer sortId = 0;
	
	/** 状态*/
	@Column(name="status")
	private int status = Constants.DISPLAY_SHOW;
}
