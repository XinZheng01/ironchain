package com.ironchain.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 需求报价
 * @author Administrator
 *
 */
public class DemandOffer {
	
	/** 设备服务*/
	public static final int TYPE_EQUIPMENT = 1;
	/** 加工需求*/
	public static final int TYPE_MACHINED = 2;
	
	/** 报价人*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member offer;
	
	/** 类型*/
	@Column(name="type")
	private int type = TYPE_EQUIPMENT;
	
	/** 需求id*/
	@Column(name="demand_id")
	private Long demandId;
	
	/** 工时费用*/
	@Column(name="hour_fee")
	private Integer hourFee;
	
	/** 材料费用*/
	@Column(name="material_fee")
	private Integer materialFee;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
	/** 状态*/
	@Column(name="status")
	private int status;
	
	/** 报价时间*/
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
}
