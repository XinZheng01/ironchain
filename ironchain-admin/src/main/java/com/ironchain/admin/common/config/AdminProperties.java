package com.ironchain.admin.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "site", ignoreUnknownFields = false)
public class AdminProperties {
	
	/** 是否不校验登录用户权限*/
	private boolean noauthor;
}
