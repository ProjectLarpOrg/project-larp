package com.projectlarp.app.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import lombok.extern.log4j.Log4j;

@Log4j
@Configuration
@EnableJpaRepositories(basePackageClasses = { //
//
//
//		XXXRepository.class, //
//
})
public class SpringJpaConfig {

	// PROPERTIES
	@Value("${hibernate.dialect:org.hibernate.dialect.Oracle10gDialect}") String dialect;
	@Value("${hibernate.jdbc.batchSize:50}") String batchSize;
	@Value("${auth.enable:true}") String FEATURETOGGLE_AUTH;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource ds) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(ds);
		
		log.warn("feature.resubmit="+FEATURETOGGLE_AUTH);
		List<String> packagesToScan = new ArrayList<String>();
		packagesToScan.add("com.projectlarp.app.modules.home");
		if(Boolean.valueOf(FEATURETOGGLE_AUTH)) {
			packagesToScan.add("com.projectlarp.app.modules.auth");
		}
		em.setPackagesToScan(packagesToScan.toArray(new String[0]));

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", dialect);
		properties.setProperty("hibernate.jdbc.batch_size", batchSize);
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
