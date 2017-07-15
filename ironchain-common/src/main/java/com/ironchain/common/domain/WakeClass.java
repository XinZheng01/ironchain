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
@Table(name="wake_class")
public class WakeClass extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 分类名称*/
	@Column(name="name")
	private String name;
	
	/** 上级分类*/
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private WakeClass parent;
	
	/** 排序值*/
	@Column(name="sort_id")
	private Integer sortId = 0;
	
}
