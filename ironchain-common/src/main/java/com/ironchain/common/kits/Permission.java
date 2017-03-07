package com.ironchain.common.kits;

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

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统权限
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "system_permission")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Permission extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 名称*/
	@Column(name="name", nullable=false, length=30)
	private String name;
	
	/** 类型*/
	@Column(name="type")
	private Integer type;
	
	/** 排序id*/
	@Column(name="order_id")
	private Integer orderId;
	
	/** 链接*/
	@Column(name="url")
	private String url;
	
	/** 图标*/
	@Column(name="icon")
	private String icon;
	
	/** 编码*/
	@Column(name="code", nullable=false, length=50)
	private String code;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
	/** 状态*/
	@Column(name="status")
	private Integer status;
	
	/** 角色*/
	@JsonIgnore
	@ManyToMany
	private Set<Role> roles = new HashSet<>(0);
}