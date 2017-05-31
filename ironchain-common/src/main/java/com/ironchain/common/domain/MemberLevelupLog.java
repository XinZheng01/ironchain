package com.ironchain.common.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironchain.common.base.DataModel;

/**
 * 会员升级记录
 * @author Administrator
 *
 */
public class MemberLevelupLog extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 会员*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	/** 开始时间*/
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	/** 会员结束时间*/
	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	/** 收入*/
	@Column(name="income")
	private BigDecimal income;
}
