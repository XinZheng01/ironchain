package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 需求分类
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name="demand_class")
public class DemandClass extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 分类名称*/
	@Column(name="name")
	private String name;
}
