package com.ironchain.common.sms;

public interface SmsService {

	/**
	 * 发送短信
	 * @param message 短信内容
	 * @param phones 手机号码
	 */
	void send(String message, String... phones);
	
	/**
	 * 发送短信
	 * @param tmpl 模板
	 * @param args 参数
	 * @param phones 手机号码
	 */
	void send(String tmpl, Object[] args, String... phones);
}
