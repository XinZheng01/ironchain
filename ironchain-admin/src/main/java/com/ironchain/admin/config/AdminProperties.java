package com.ironchain.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "site", ignoreUnknownFields = false)
public class AdminProperties {
	
	/** 是否不校验登录用户权限*/
	private boolean noauthor;
	
	/** 上传文件的文件系统根路径*/
	private String uploadRootPath;
	
	/** 上传文件的URL根路径*/
	private String uploadBaseUrl;
}
