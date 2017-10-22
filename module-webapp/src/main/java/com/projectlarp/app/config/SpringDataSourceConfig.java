package com.projectlarp.app.config;

import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SpringDataSourceConfig {

	// PROPERTIES
	@Value("${datasource.jndiname:DS}") 	private String jndi;
	@Value("${datasource.driverClassName}") 	private String driverClassName;
	@Value("${datasource.url}") 	private String url;
	@Value("${datasource.username}") 	private String username;
	@Value("${datasource.password}") 	private String password;

	private static final Logger log = Logger.getLogger(SpringDataSourceConfig.class);

	@Bean
	public DataSource dataSource() throws Exception {
		DataSource ds;
		try {
			ds = dataSourceFromJNDI();
		} catch (NamingException e) {
			log.info("no JNDI dataSource.");
			ds = datasourceFromProperties();
		}
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	DataSource datasourceFromProperties() throws SQLException {
		log.info("trying with Properties dataSource... url=" + url);
		return DataSourceBuilder.create() //
				.driverClassName(driverClassName) //
				.url(url) //
				.username(username) //
				.password(password) //
				.build();
	}

	DataSource dataSourceFromJNDI() throws NamingException {
		log.info("trying with JNDI dataSource... jndi=" + jndi);
		return (DataSource) new InitialContext() //
				.lookup(jndi);
	}

}