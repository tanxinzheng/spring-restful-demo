package com.github.tanxinzheng.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.config.EnableHypermediaSupport;

/**
 * Spring Boot launch file.
 */
@SpringBootApplication
@EnableAutoConfiguration
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}