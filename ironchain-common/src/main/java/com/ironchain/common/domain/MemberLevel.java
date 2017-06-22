package com.ironchain.common.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 会员等级管理
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="member_level")
public class MemberLevel extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 名称*/
	@Column(name="name")
	private String name;
	
	/** 价格*/
	@Column(name="price")
	private BigDecimal price;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
}
