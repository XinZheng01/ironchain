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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironchain.common.base.DataModel;
import com.ironchain.common.domain.Constants.RegexConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * 会员
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name="member")
public class Member extends DataModel{
	
	private static final long serialVersionUID = 1L;
	
	/** 个人*/
	public final static int TYPE_PERSON = 1;
	/** 企业*/
	public final static int TYPE_COMPANY = 2;
	
	public final static int STATUS_SUCCESS = 1;
	public final static int STATUS_AUDIT = 2;
	public final static int STATUS_LOCK = 3;
	
	/** 用户名*/
	@Column(name="name")
	private String name;
	
	/** 用户头像*/
	@Column(name="head_img")
	private String headImg;
	
	/** 类型*/
	@Column(name="type")
	private int type = TYPE_PERSON;
	
	/** 邮箱*/
	@Column(name="email")
	private String email;
	
	/** 手机*/
	@NotNull(message="手机号码不能为空")
	@Pattern(regexp=RegexConstants.MOBILE_REGEX, message="手机号码格式不正确")
	@Column(name="mobilephone")
	private String mobilephone;
	
	/** 密码*/
	@NotNull(message="密码不能为空")
	@Column(name="password")
	private String password;
	
	/** 身份证*/
	@Column(name="idcard")
	private String idcard;
	
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
	@JoinTable(name="member_equ_ref",
		joinColumns={@JoinColumn(name="member_id")},
		inverseJoinColumns={@JoinColumn(name="equ_id")})
	private Set<EquipmentClass> companyEquipment;
	
	/** 最后登录时间*/
	@Column(name="last_login_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;
	
	/** 状态 1正常 2审核中 3锁定*/
	@Column(name="status")
	private int status = STATUS_SUCCESS;
	
	public String getTypeStr(){
		switch (this.type) {
		case 1:
			return "个人";
		case 2:
			return "企业";
		default:
			return null;
		}
	}
}
