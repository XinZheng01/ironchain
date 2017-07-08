package com.ironchain.common.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备分类
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name="equipment_class")
public class EquipmentClass extends DataModel {

	private static final long serialVersionUID = 1L;
	
	/** 设备类型*/
	@Column(name="type")
	private int type;
	
	/** 设备名称*/
	@Column(name="name")
	private String name;
	
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>(2);
		map.put("id", id);
		map.put("name", name);
		return map;
	}
}
