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
	
	/** 待审核*/
	public static final int PROGRESS_AUDIT = 1;
	/** 发布*/
	public static final int PROGRESS_PUBLISH = 2;
	/** 竞标中*/
	public static final int PROGRESS_BID = 3;
	/** 待发货*/
	public static final int PROGRESS_SEND = 4;
	/** 待付款*/
	public static final int PROGRESS_PAY = 5;
	/** 已完成*/
	public static final int PROGRESS_COMPLETE = 6;
	/** 已过期*/
	public static final int PROGRESS_OVER = 7;
	/** 已作废*/
	public static final int PROGRESS_CANCEL = 8;
	
	/** 加工需求*/
	public static final int TYPE_MACHINED = 1;
	/** 设备服务*/
	public static final int TYPE_EQUIPMENT = 2;
	
	/** 标题*/
	@Column(name="title")
	private String title;
	
	/** 数量*/
	@Column(name="number")
	private long number;
	
	/** 预算*/
	@Column(name="budget")
	private long budget;
	
	/** 类型*/
	@Column(name="type")
	private int type = TYPE_MACHINED;
	
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
	private int progress = PROGRESS_AUDIT;
	
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

	public static String parseProgress(int progress){
		switch (progress) {
		case PROGRESS_AUDIT:
			return "待审核";
		case PROGRESS_PUBLISH:
			return "已发布";
		case PROGRESS_BID:
			return "竞标中";
		case PROGRESS_SEND:
			return "待发货";
		case PROGRESS_PAY:
			return "待付款";
		case PROGRESS_COMPLETE:
			return "已完成";
		case PROGRESS_OVER:
			return "已过期";
		case PROGRESS_CANCEL:
			return "已作废";
		default:
			return null;
		}
	}
	
	public static String parseType(int type){
		switch (type) {
		case TYPE_MACHINED:
			return "加工需求";
		case TYPE_EQUIPMENT:
			return "设备服务";
		default:
			return null;
		}
	}
}
