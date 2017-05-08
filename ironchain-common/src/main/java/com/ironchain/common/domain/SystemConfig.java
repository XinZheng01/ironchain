package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统配置
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name = "system_config")
public class SystemConfig extends DataModel{

	private static final long serialVersionUID = 1L;
	
	/** 参数名*/
	@NotBlank(message="参数名不能为空")
	@Column(name="`key`")
	private String key;
	
	/** 参数值*/
	@NotBlank(message="参数值不能为空")
	@Column(name="`value`")
	private String value;
	
	/** 所属参数组*/
	@Column(name="`group`")
	private String group;
	
	/** 描述*/
	@Column(name="description")
	private String description;
	
}
