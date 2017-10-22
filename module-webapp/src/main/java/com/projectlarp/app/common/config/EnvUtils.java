package com.projectlarp.app.common.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

public class EnvUtils {

	@SuppressWarnings("rawtypes")
	public static Map<String, String> getProperties(Environment env) {
		Map<String, String> rtn = new HashMap<String, String>();
		if (env instanceof ConfigurableEnvironment) {
			for (PropertySource<?> propertySource : ((ConfigurableEnvironment) env)
					.getPropertySources()) {
				if (propertySource instanceof EnumerablePropertySource) {
					for (String key : ((EnumerablePropertySource) propertySource)
							.getPropertyNames()) {
						rtn.put(key, (String) propertySource.getProperty(key));
					}
				}
			}
		}
		return rtn;
	}
}
