package com.ironchain.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironchain.common.base.BaseModel;

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
@Table(name="equipment_demand")
public class EquipmentDemand extends BaseModel {

	private static final long serialVersionUID = 1L;
	/** 新建*/
	public static final int progress_create = 1;
	/** 发布*/
	public static final int progress_publish = 2;
	/** 竞标中*/
	public static final int progress_bid = 3;
	
	
	/** 标题*/
	private String title;
	
	/** 数量*/
	private String number;
	
	/** 品牌*/
	private String brand;
	
	/** 型号*/
	private String kinds;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
	/** 开始时间*/
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	/** 结束时间*/
	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	/** 进度*/
	@Column(name="progress")
	private int progress;
}
