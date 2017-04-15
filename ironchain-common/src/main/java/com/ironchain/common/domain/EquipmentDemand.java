package com.ironchain.common.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ironchain.common.base.DataModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备服务需求
 * @author Administrator
 *
 */
@Getter
@Setter
@Entity
@Table(name="equipment_demand")
public class EquipmentDemand extends DataModel {

	private static final long serialVersionUID = 1L;
	
	private String name;
}
