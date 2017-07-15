package com.ironchain.common.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 需求报价
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name="demand_offer")
public class DemandOffer extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	/** 报价*/
	public static final int STATUS_CREATE = 1;
	
	/** 选中*/
	public static final int STATUS_SELECT = 2;
	
	/** 报价人*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member offer;
	
	/** 需求id*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="demand_id")
	private Demand demand;
	
	/** 价格*/
	@Column(name="price")
	private BigDecimal price;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
	/** 状态*/
	@Column(name="status")
	private int status = STATUS_CREATE;
	
	/** 创建时间*/
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/** 更新时间*/
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
}
