package com.ironchain.intfc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = { "com.ironchain.intfc.common.config", 
		"com.ironchain.intfc.modules"})
@EnableCaching
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).run(args);
	}
}