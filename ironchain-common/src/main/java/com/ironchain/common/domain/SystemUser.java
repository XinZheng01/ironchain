package com.ironchain.common.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironchain.common.base.DataModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="system_user")
public class SystemUser extends DataModel {
	private static final long serialVersionUID = 1L;
	
	public static final int STATUS_DELETE = 0;
	public static final int STATUS_SUCCESS = 1;
	public static final int STATUS_LOCK = 2;
	
	/** 登录名*/
	@NotNull
	@Length(min = 5, max = 30)
	@Column(name="login_name", unique = true, nullable=false, length=30)
	private String loginName;

	/** 密码*/
	@JsonIgnore
	@Length(min = 6, max = 20)
	@Column(name="password", length=60, nullable=false)
	private String password;
	
	/** 用户名*/
	@Length(min = 2, max = 10)
	@Column(name="name", length=20)
	private String name;
	
	/** 邮箱*/
	@Column(name="email", length=50)
	@Pattern(regexp=RegeConstants.EMAIL_REGEX)
	private String email;
	
	/** 手机号码*/
	
	@Column(name="mobilephone", length=20)
	@Pattern(regexp=RegeConstants.MOBILE_REGEX)
	private String mobilephone;
	
	/** 状态 0：删除 1：正常 2：锁定*/
	@Column(name="status", nullable=false)
	private int status = STATUS_SUCCESS;
	
	/** 用户角色*/
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="sys_user_role_ref",
		joinColumns={@JoinColumn(name="user_id")},
		inverseJoinColumns={@JoinColumn(name="role_id")})
	private Set<SystemRole> roles = new HashSet<>(0);
	
	public String getStatusStr(){
		switch (this.status) {
		case 0:
			return "删除";
		case 1:
			return "正常";
		case 2:
			return "锁定";
		}
		return null;
	}
}