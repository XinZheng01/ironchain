package com.ironchain.common.domain;

public final class Constants {
	
	private Constants(){}
	
	/**
	 * 正则常量 
	 * @author Administrator
	 */
	public final class RegexConstants{
		/** 用户登录名正则*/
		public static final String LOGIN_REGEX = "^[_A-Za-z0-9]*$";
		/** 用户邮箱正则*/
		public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		/** 用户手机正则*/
		public static final String MOBILE_REGEX = "^(13|15|18|14|17)[0-9]{9}$";
	}
	
	/**
	 * 缓存常量
	 * @author Administrator
	 */
	public enum CacheConstants{
		/** 验证码 失效时间3分钟*/
		VERIFYCODE("ironchain.verifycode:", 60 * 3),
		
		/** 登录令牌 失效时间1个月*/
		LOGIN_TOKEN("ironchain.login.token:", 30 * 24 * 60 * 60),
		
		/** 登录名称 失效时间1个月*/
		LOGIN_NAME("ironchain.login.name:", 30 * 24 * 60 * 60),
		
		/** 修改密码 30分钟有效*/
		RESET_PASSWORD("ironchain.reset.password:", 30 * 60);
		
		private String prefix;
		private int expiredTime;

		CacheConstants(String prefix, int expiredTime) {
			this.prefix = prefix;
			this.expiredTime = expiredTime;
		}

		public String getPrefix() {
			return prefix;
		}

		public int getExpiredTime() {
			return expiredTime;
		}
		
		public String getKey(String key){
			return this.prefix + key;
		}
		
	}
}
