package org.projectlarp.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @see https://spring.io/blog/2013/12/19/serving-static-web-content-with-spring-boot
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final String[] RESOURCE_LOCATIONS = { //
			"classpath:/META-INF/resources/", "classpath:/resources/", //
			"classpath:/static/", //
			"classpath:/public/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**") //
					.addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**") //
					.addResourceLocations(RESOURCE_LOCATIONS);
		}
	}
}