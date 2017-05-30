package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 需求文件
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name="demand_file")
public class DemandFile extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	/** 设备服务*/
	public static final int TYPE_EQUIPMENT = 1;
	/** 加工需求*/
	public static final int TYPE_MACHINED = 2;
	
	/** 类型*/
	@Column(name="type")
	private int type = TYPE_EQUIPMENT;
	
	/** 需求id*/
	@Column(name="demand_id")
	private Long demandId;
	
	/** 文件路径*/
	@Column(name="path")
	private String path;
}
