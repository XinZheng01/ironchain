	
	
	
	
package com.ironchain.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ironchain.common.base.BaseModel;

import lombok.Getter;
import lombok.Setter;
/**
 * 工厂位置
 * 
 * @author zheng xin
 * @email 
 */
@Setter
@Getter
@Entity
@Table(name="factory_location")
public class FactoryLocation extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	/** 企业*/
	@Column(name="member_id")
	private Long memberId;
	
	/** 区域*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="area_id")
	private GdArea area;
	
	/** 详细地址*/
	@Column(name="address")
	private String address;
	
	/** 地图位置*/
	@Column(name="location")
	private String location;
	
}
