package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备服务需求
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name="demand_equipment")
public class DemandEquipment extends BaseDemand {

	private static final long serialVersionUID = 1L;
	/** 新建*/
	public static final int progress_create = 1;
	/** 发布*/
	public static final int progress_publish = 2;
	/** 竞标中*/
	public static final int progress_bid = 3;
	
	/** 品牌*/
	@Column(name="brand")
	private String brand;
	
	/** 型号*/
	@Column(name="kinds")
	private String kinds;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
	/** 年限*/
	@Column(name="life")
	private Integer life;
	
	/** 进度*/
	@Column(name="progress")
	private int progress;
	
}