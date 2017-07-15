package com.ironchain.common.domain;

import java.math.BigDecimal;
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
	
	/** 审核中*/
	public static final int STATUS_AUDIT = 1;
	/** 通过*/
	public static final int STATUS_SUCCESS = 2;
	/** 失败*/
	public static final int STATUS_FAIL = 0;
	
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
	
	/** 状态*/
	@Column(name="status")
	private int status = STATUS_AUDIT;
	
	/** 业务类型*/
	@Column(name="service_type")
	private Integer serviceType;
	
	/** 企业名称*/
	@Column(name="company_name")
	private String companyName;
	
	/** 企业法人*/
	@Column(name="company_legal")
	private String companyLegal;
	
	/** 企业法人电话*/
	@Column(name="company_legal_phone")
	private String companyLegalPhone;
	
	/** 企业法人身份证*/
	@Column(name="company_idcard")
	private String companyIdcard;
	
	/** 企业固定电话*/
	@Column(name="company_tel")
	private String companyTel;
	
	/** 企业能达到的精度*/
	@Column(name="company_precision")
	private BigDecimal companyPrecision;
	
	/** 企业营业执照路径*/
	@Column(name="company_license_img")
	private String companyLicenseImg;
	
	/** 企业地址*/
	@Column(name="company_address")
	private String companyAddress;
	
	/** 企业设备*/
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="levelup_equ_ref",
		joinColumns={@JoinColumn(name="levelup_id")},
		inverseJoinColumns={@JoinColumn(name="equ_id")})
	private Set<EquipmentClass> companyEquipment;
}
