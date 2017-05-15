package com.ironchain.common.sms;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class SmsServiceImpl implements SmsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

	@Autowired
	private MessageSource messageSource;

	@Override
	public void send(String message, String... phones) {
		LOGGER.info("发送短信:{}, 手机号码:{}", message, Arrays.toString(phones));
	}

	@Override
	public void send(String tmpl, Object[] args, String... phones) {
		send(messageSource.getMessage(tmpl, args, LocaleContextHolder.getLocale()), phones);
	}

}
