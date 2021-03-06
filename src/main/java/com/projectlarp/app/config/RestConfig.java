package com.projectlarp.app.config;

import com.projectlarp.app.admin.Profile;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.projectlarp.app.security.domain.model.User;

@Configuration
public class RestConfig extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor( //
				User.class, Profile.class //
		);
	}

}