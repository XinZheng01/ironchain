package com.ironchain.common.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SystemPermission extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 名称*/
	@Column(name="name", nullable=false, length=30, columnDefinition="名称")
	private String name;
	
	/** 类型 1:目录,2:菜单,3:按钮*/
	@Column(name="type", columnDefinition="类型 1:目录,2:菜单,3:按钮")
	private Integer type;
	
	/** 链接*/
	@Column(name="url", columnDefinition="链接")
	private String url;
	
	/** 图标*/
	@Column(name="icon", columnDefinition="图标")
	private String icon;
	
	/** 编码*/
	@Column(name="code", nullable=false, length=50, columnDefinition="编码")
	private String code;
	
	/** 描述*/
	@Column(name="description", columnDefinition="描述")
	private String description;
	
	/** 状态*/
	@Column(name="status", columnDefinition="状态")
	private Integer status;
	
	/** 父权限*/
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="parent_id", foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT), columnDefinition="父权限")
	private SystemPermission parent;
	
	/** 角色*/
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="sys_role_perm_ref",
		joinColumns={@JoinColumn(name="permission_id")},
		inverseJoinColumns={@JoinColumn(name="role_id")},
		foreignKey=@ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Set<SystemRole> roles = new HashSet<>(0);
}