package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 加工需求
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name="demand_machined")
public class DemandMachined extends BaseDemand {

	private static final long serialVersionUID = 1L;
	/** 新建*/
	public static final int progress_create = 1;
	/** 发布*/
	public static final int progress_publish = 2;
	/** 竞标中*/
	public static final int progress_bid = 3;
	
	/** 材料*/
	@Column(name="machined")
	private String machined;
	
	/** 交期天数*/
	@Column(name="term")
	private Integer term;
	
	/** 付款描述*/
	@Column(name="payment")
	private String payment;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
	/** 进度*/
	@Column(name="progress")
	private int progress;
	
}
