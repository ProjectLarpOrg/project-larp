package com.projectlarp.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class SpringPropertyConfig {

	@Bean
	PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer bean = new PropertySourcesPlaceholderConfigurer();
		bean.setNullValue("UNKNOW");
		bean.setIgnoreUnresolvablePlaceholders(true);
		bean.setIgnoreResourceNotFound(true);
		bean.setLocations(new Resource[] { //
				//
				new ClassPathResource("application.properties"), //
				new ClassPathResource("build.properties") //
		});
		return bean;
	}

}
