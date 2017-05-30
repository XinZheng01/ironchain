package com.ironchain.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironchain.common.base.BaseModel;

@MappedSuperclass
public class BaseDemand extends BaseModel {

	private static final long serialVersionUID = -1461917182838548671L;
	
	/** 标题*/
	@Column(name="title")
	private String title;
	
	/** 数量*/
	@Column(name="number")
	private String number;
	
	/******** 竞价条件 **********/
	/** 区域名称*/
	@Column(name="area_name")
	private String areaName;
	
	/** 区域地理位置*/
	@Column(name="location")
	private String location;
	
	/** 竞方数量*/
	@Column(name="bid_number")
	private Integer bidNumber;
	
	/** 主要设备*/
	@Column(name="main_equipment")
	private String mainEquipment;
	
	/** 开始时间*/
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	/** 结束时间*/
	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	/**************************/
	
	/** 发布人*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member publisher;
	
	/** 需求分类*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="class_id")
	private DemandClass demandClass;
}
