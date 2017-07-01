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

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 会员升级记录
 * @author Administrator
 *
 */
@Setter
@Getter
@Entity
@Table(name="member_levelup")
public class MemberLevelup extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 会员*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	private Member member;
	
	/** 会员等级*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_level_id")
	private MemberLevel level;
	
	/** 会员开始时间*/
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	/** 会员结束时间*/
	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	/** 平台收入*/
	@Column(name="income")
	private BigDecimal income;
	
}
