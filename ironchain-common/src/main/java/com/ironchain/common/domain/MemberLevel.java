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
	
	/** 状态*/
	@Column(name="status")
	private int status = Constants.DISPLAY_SHOW;
	
	/** 需求重置次数*/
	@Column(name="reset_count")
	private int resetCount = 0;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
	public String getStatusStr(){
		switch (this.status) {
		case Constants.DISPLAY_SHOW:
			return "启用";
		case Constants.DISPLAY_UNSHOW:
			return "停用";
		default:
			return null;
		}
	}
}
