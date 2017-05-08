package com.ironchain.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.ironchain.admin.common.config.AdminProperties;

@SpringBootApplication(scanBasePackages = {"com.ironchain.admin"})
@EnableConfigurationProperties(AdminProperties.class)
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).run(args);
	}
}