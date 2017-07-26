package com.ironchain.common.domain;

public final class Constants {
	
	private Constants(){}
	
	/** 显示状态 停用*/
	public static final int DISPLAY_UNSHOW = 0;
	/** 显示状态 启用*/
	public static final int DISPLAY_SHOW = 1;
	
	/** 判断 否*/
	public static final int JUDGE_NO = 0;
	/** 判断 是*/
	public static final int JUDGE_YES = 1;
	
	/**
	 * 日期常量
	 * @author Administrator
	 *
	 */
	public final class DateConstants{
		public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
		public static final String YYYYMMDD = "yyyy-MM-dd";
		public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
		public static final String HHMMSS = "HH:mm:ss";
	}
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
		/** 手机号码或固话正则*/
		public static final String TEL_REGEX = "^(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}$";
	}
	
	/**
	 * 缓存常量
	 * @author Administrator
	 */
	public enum CacheConstants{
		
		/** 验证码 失效时间10分钟*/
		VERIFYCODE("ironchain.verifycode:", 60 * 10),
		
		/** 登录令牌 失效时间1个月*/
		LOGIN_TOKEN("ironchain.login.token:", 30 * 24 * 60 * 60),
		
		/** 登录名称 失效时间1个月*/
		LOGIN_NAME("ironchain.login.name:", 30 * 24 * 60 * 60),
		
		/** 修改密码 30分钟有效*/
		RESET_PASSWORD("ironchain.reset.password:", 30 * 60),
		
		/** 购物车 失效时间1个月*/
		SHOP_CART("ironchain.shop.cart:", 30 * 24 * 60 * 60);
		
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
