package com.projectlarp.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class SpringRestMvcConfiguration extends RepositoryRestMvcConfiguration {

  @Override
  public RepositoryRestConfiguration config() {
    RepositoryRestConfiguration config = super.config();
    config.setBasePath("/api/");
    return config;
  }
  /*
  @Override
  protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
      config.exposeIdsFor( //
//    		  Exchange.class, //
//    		  ServiceFlow.class //
    		  );
  }
  */
}