package com.ironchain.common.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统权限
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name = "system_permission")
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SystemPermission extends DataModel {

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_CATALOG = 1;
	public static final int TYPE_MENU = 2;
	public static final int TYPE_BTN = 3;
	
	public static final int STATUS_UNSHOW = 0;
	public static final int STATUS_SHOW = 1;
	
	/** 名称*/
	@Column(name="name", nullable=false, length=30)
	private String name;
	
	/** 编码*/
	@Column(name="code", length=50)
	private String code;
	
	/** 类型 1:目录,2:菜单,3:按钮*/
	@Column(name="type")
	private int type = TYPE_CATALOG;
	
	/** 链接*/
	@Column(name="url")
	private String url;
	
	/** 图标*/
	@Column(name="icon")
	private String icon;
	
	/** 排序值*/
	@Column(name="order_num")
	private int orderNum = 0;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
	/** 状态 1 启用 0 停用*/
	@Column(name="status")
	private int status = STATUS_SHOW;
	
	/** 父权限*/
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id")
	private SystemPermission parent;
	
	/** 子权限*/
	@JsonIgnore
	@OneToMany(mappedBy="parent", fetch=FetchType.LAZY)
	private Set<SystemPermission> children = new HashSet<>(0);
	
	/** 角色*/
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="sys_role_perm_ref",
		joinColumns={@JoinColumn(name="permission_id")},
		inverseJoinColumns={@JoinColumn(name="role_id")})
	private Set<SystemRole> roles = new HashSet<>(0);

	public String getTypeStr(){
		switch (type) {
		case 1:
			return "目录";
		case 2:
			return "菜单";
		case 3:
			return "权限";
		}
		return null;
	}
	
	public String getStatusStr(){
		switch (status) {
		case 1:
			return "启用";
		case 0:
			return "停用";
		}
		return null;
	}
}