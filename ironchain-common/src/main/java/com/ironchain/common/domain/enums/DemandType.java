package com.ironchain.common.domain.enums;

/**
 * 需求类型
 * @author Administrator
 *
 */
public enum DemandType {
	
	MACHINED(1, "五金"),
	EQUIPMENT(2, "维修"),
	PLASTIC(3, "塑胶"),
	ELECTRONIC(4, "电子");
	
	int type;
	
	String chineseName;
	
	private DemandType(int type, String chineseName){
		this.type = type;
		this.chineseName = chineseName;
	}
	
	public int getType(){
		return type;
	}
	
	public String getChineseName(){
		return chineseName;
	}
	
	public static DemandType parse(int type){
		for (DemandType demandType : DemandType.values()) {
			if(demandType.getType() == type)
				return demandType;
		}
		return null;
	}
}
