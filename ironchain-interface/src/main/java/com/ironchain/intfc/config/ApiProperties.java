package com.ironchain.intfc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "site", ignoreUnknownFields = false)
public class ApiProperties {
	
	/** 接口加密aes私钥*/
	private String aesKey;
	
	/** 接口是否加密*/
	private boolean apiDigest;
	
}