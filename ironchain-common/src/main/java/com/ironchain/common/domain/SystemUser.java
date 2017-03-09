package com.ironchain.common.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	/** 登录名*/
	@NotNull
	@Size(min = 5, max = 30)
	@Column(name="login_name", unique = true, nullable=false, length=30)
	private String loginName;

	/** 密码*/
	@JsonIgnore
	@NotNull
	@Column(name="password",length = 60)
	private String password;
	
	/** 用户名*/
	@Column(name="name")
	private String name;
	
	/** 状态*/
	@Column(name="status")
	private Integer status;
	
	/** 用户角色*/
	@JsonIgnore
	@ManyToMany
	private Set<SystemRole> roles = new HashSet<>(0);
}