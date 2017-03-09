package com.ironchain.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = { "com.ironchain.admin.common.config", 
		"com.ironchain.admin.modules"})
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).run(args);
	}
}