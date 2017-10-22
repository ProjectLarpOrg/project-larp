package com.projectlarp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.projectlarp.app.config.SpringDataSourceConfig;
import com.projectlarp.app.modules.SpringModulesConfig;

/**
 * @see https://spring.io/blog/2014/03/07/deploying-spring-boot-applications
 */
@SpringBootApplication(scanBasePackageClasses = { //
		SpringDataSourceConfig.class, //
		SpringModulesConfig.class })
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
