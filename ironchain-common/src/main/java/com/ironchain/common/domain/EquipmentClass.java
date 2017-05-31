package com.ironchain.common.domain;

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
	
	private String name;
}
