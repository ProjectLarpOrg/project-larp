package org.projectlarp.test.util;

import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static java.lang.String.format;

public class JDBCClient {

	private String url;
	private String user;
	private String password;
	private String driverClassName;

	public JDBCClient(Properties props) {
		url = props.getProperty("url");
		user = props.getProperty("user");
		password = props.getProperty("password");
		driverClassName = props.getProperty("driver");
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private JdbcTemplate getJdbc() {
		DriverManagerDataSource datasource = new DriverManagerDataSource(url,
				user, password);
		datasource.setDriverClassName(driverClassName);
		return new JdbcTemplate(datasource);
	}

	public void execute(String sql) {
		getJdbc().execute(sql);
	}

	public Object query(String sql, ResultSetExtractor<?> extractor) {
		return getJdbc().query(sql, extractor);
	}
	
	public int update(String sql) {
		return getJdbc().update(sql);
	}

	public long count(String sql) {
		return getJdbc().queryForObject(sql, Long.class);
	}

	public void truncate(String table) {
		getJdbc().update(format("TRUNCATE TABLE %s", table));
	}
}
