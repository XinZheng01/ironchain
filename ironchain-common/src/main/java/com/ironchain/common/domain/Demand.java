package com.ironchain.common.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="demand")
public class Demand extends BaseModel {

	private static final long serialVersionUID = 1;
	
	/** 新建*/
	public static final int PROGRESS_CREATE = 1;
	/** 发布*/
	public static final int PROGRESS_PUBLISH = 2;
	/** 竞标中*/
	public static final int PROGRESS_BID = 3;
	
	/** 标题*/
	@Column(name="title")
	private String title;
	
	/** 数量*/
	@Column(name="number")
	private String number;
	
	/******** 竞价条件 **********/
	/** 区域*/
	@Column(name="adcode")
	private GdArea gdArea;
	
	/** 竞方数量*/
	@Column(name="bid_number")
	private Integer bidNumber;
	
	/** 主要设备*/
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="demand_equ_ref",
		joinColumns={@JoinColumn(name="demand_id")},
		inverseJoinColumns={@JoinColumn(name="equ_id")})
	private Set<EquipmentClass> mainEquipment;
	
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
	
	/** 进度*/
	@Column(name="progress")
	private int progress = PROGRESS_CREATE;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
	/** 创建时间*/
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/** 更新时间*/
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	/*********加工需求**********/
	/** 材料*/
	@Column(name="machined")
	private String machined;
	
	/** 交期天数*/
	@Column(name="term")
	private Integer term;
	
	/** 付款描述*/
	@Column(name="payment")
	private String payment;
	/**************************/
	
	/*********设备服务**********/
	/** 品牌*/
	@Column(name="brand")
	private String brand;
	
	/** 型号*/
	@Column(name="kinds")
	private String kinds;
	
	/** 年限*/
	@Column(name="life")
	private Integer life;
	/**************************/
	
}
