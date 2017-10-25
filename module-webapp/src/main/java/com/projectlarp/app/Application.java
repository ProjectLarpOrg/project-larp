package com.projectlarp.app;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

import com.projectlarp.app.config.ApplicationConfig;
import com.projectlarp.app.modules.ModulesConfig;

/**
 * @see https://spring.io/blog/2014/03/07/deploying-spring-boot-applications
 */
@SpringBootApplication(scanBasePackageClasses = { //
		ApplicationConfig.class, //
		ModulesConfig.class })
@EnableScheduling
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		configureApplication(new SpringApplicationBuilder()).run(args);
	}

	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
		return builder.sources(Application.class).bannerMode(Banner.Mode.OFF);
	}
}
