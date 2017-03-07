package com.ironchain.common.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironchain.common.base.DataModel;
import com.ironchain.common.kits.Role;

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
	@Column(name="login_name", nullable=false, length=30)
	private String loginName;

	/** 密码*/
	@Column(name="password")
	private String password;
	
	/** 密码盐*/
	@Column(name="salt")
	private String salt;
	
	/** 用户名*/
	@Column(name="name")
	private String name;
	
	/** 状态*/
	@Column(name="status")
	private Integer status;
	
//	/** 用户角色*/
//	@JsonIgnore
//	@ManyToMany
//	private Set<Role> roles = new HashSet<>(0);
}