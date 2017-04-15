package com.ironchain.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironchain.common.base.DataModel;

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
	public final static int type_person = 1;
	/** 企业*/
	public final static int type_company = 2;
	
	/** 用户名*/
	@Column(name="name")
	private String name;
	
	/** 用户头像*/
	@Column(name="head")
	private String head;
	
	/** 类型*/
	@Column(name="type")
	private int type;
	
	/** 邮箱*/
	@Column(name="email")
	private String email;
	
	/** 手机*/
	@Column(name="mobilephone")
	private String mobilephone;
	
	/** 密码*/
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
	
	/** 企业法人身份证*/
	@Column(name="company_idcard")
	private String companyIdcard;
	
	/** 企业电话*/
	@Column(name="company_phone")
	private String companyPhone;
	
	/** 企业营业执照路径*/
	@Column(name="company_license")
	private String companyLicense;
	
	/** 企业地址*/
	@Column(name="company_address")
	private String companyAddress;
	
	/** 最后登录时间*/
	@Column(name="last_login_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;
}
