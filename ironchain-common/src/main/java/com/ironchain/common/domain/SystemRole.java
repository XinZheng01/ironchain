package com.ironchain.common.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironchain.common.base.DataModel;
import com.ironchain.common.domain.SystemUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统角色
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "system_role")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SystemRole extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 名称*/
	@Column(name="name")
	private String name;
	
	/** 编码*/
	@Column(name="code")
	private String code;
	
//	/** 排序id*/
//	@Column(name="order_id")
//	private Integer orderId;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
	/** 状态*/
	@Column(name="status")
	private Integer status;
	
	/** 用户*/
	@JsonIgnore
	@ManyToMany
	private Set<SystemUser> users = new HashSet<>(0);
	
	/** 权限列表*/
	@JsonIgnore
	@ManyToMany
	private Set<SystemPermission> permissions = new HashSet<>(0);

}