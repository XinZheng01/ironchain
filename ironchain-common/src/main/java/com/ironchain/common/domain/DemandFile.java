package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
	
	/** 图档*/
	public static final int TYPE_FILE = 1;
	/** 故障*/
	public static final int TYPE_FAULT = 2;
	/** 外观*/
	public static final int TYPE_LOOKS = 3;
	
	/** 类型*/
	@Min(value=TYPE_FILE, message="非法需求文件类型")
	@Max(value=TYPE_LOOKS, message="非法需求文件类型")
	@Column(name="type")
	private int type = TYPE_FILE;
	
	/** 需求id*/
	@Column(name="demand_id")
	private Long demandId;
	
	/** 文件名称*/
	@Column(name="name")
	private String name;
	
	/** 文件路径*/
	@NotNull(message="文件路径不能为空")
	@Column(name="path")
	private String path;
}
