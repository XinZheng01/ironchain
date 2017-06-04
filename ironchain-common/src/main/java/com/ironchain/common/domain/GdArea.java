package com.ironchain.common.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 高德省市区
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name="gd_area")
public class GdArea implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 所属省编码*/
	@Column(name="provcode")
	private String provcode;
	
	/** 所属市编码*/
	@Column(name="citycode")
	private String citycode;
	
	/** 区域编码*/
	@Id
	@Column(name="adcode")
	private String adcode;
	
	/** 名称*/
	@Column(name="name")
	private String name;
	
	/** 中心区位置*/
	@Column(name="center")
	private String center;
	
	/** 级别 country province city district*/
	@Column(name="level")
	private String level;
	
	public Map<String, Object> toSimpleMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("adcode", adcode);
		map.put("name", name);
		return map;
	}
}
