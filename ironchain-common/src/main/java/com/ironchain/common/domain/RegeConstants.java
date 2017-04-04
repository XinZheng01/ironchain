package com.ironchain.common.domain;

public final class RegeConstants {
	
	/** 用户登录名正则*/
	public static final String LOGIN_REGEX = "^[_A-Za-z0-9]*$";
	/** 用户邮箱正则*/
	public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	/** 用户手机正则*/
	public static final String MOBILE_REGEX = "^(13|15|18|14|17)[0-9]{9}$";
	
	private RegeConstants(){}
}
