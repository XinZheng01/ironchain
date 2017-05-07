package com.ironchain.intfc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

import com.ironchain.intfc.config.ApiProperties;

@SpringBootApplication(scanBasePackages = { "com.ironchain.intfc"})
@EnableCaching
@EnableConfigurationProperties(ApiProperties.class)
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).run(args);
	}
}