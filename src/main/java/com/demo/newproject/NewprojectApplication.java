package com.demo.newproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.demo.newporject.mapper")
@ComponentScan(value = "com.demo.newproject.config")
@ComponentScan(value = "com.hanchen.distrubuted.component.Intercept")
public class NewprojectApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(NewprojectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(NewprojectApplication.class, args);
	}

}
