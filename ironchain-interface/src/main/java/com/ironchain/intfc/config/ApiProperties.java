package com.ironchain.intfc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties("site")
public class ApiProperties {
	
	private String aesKey;
	
	private boolean apiDigest;
	
}
