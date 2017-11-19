package com.projectlarp.app.config;

import java.sql.*;
import javax.sql.*;

import org.springframework.context.annotation.*;
import org.springframework.boot.autoconfigure.jdbc.*;
import org.springframework.boot.context.properties.*;
import org.springframework.jdbc.core.*;

@Configuration
public class SpringDataSourceConfig {

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	@ConfigurationProperties(prefix = "datasource")
	public DataSource dataSourceProperties() throws SQLException {
		return DataSourceBuilder //
				.create() //
				.build();
	}
}