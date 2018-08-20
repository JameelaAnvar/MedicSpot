package com.innvent.medicspot.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PostgresDataSourceConfig {

	@Bean
	DataSource getDataSource() throws SQLException {

		DataSourceBuilder dsb = DataSourceBuilder.create();
		// Adil cf database
		/*
		 * DataSource ds =
		 * dsb.username("YHCInU1PcqQvV_iM").password("dUZOWvu43fK7NTA7")
		 * .url("jdbc:postgresql://10.11.241.47:42888/Q1WVjEu8nxqbBQ3V")
		 * .driverClassName("org.postgresql.Driver").build();
		 */

		// Himanshu cf DataSource connect
		/*
		 * DataSource ds =
		 * dsb.username("CuLy38hjO9l0hezP").password("q5_d29iSwQ2Q7uuW")
		 * .url("jdbc:postgresql://10.11.241.24:43241/P11OU0gucj2U7kiY")
		 * .driverClassName("org.postgresql.Driver").build();
		 */

		// Jameela cf DataSource connect
		DataSource ds = dsb.username("cAehgkdFIdC-0nJv").password("Cp1HNwyy6Q_isbj3")
				.url("jdbc:postgresql://10.11.241.0:59486/guddVbgkSIZsAM7O").driverClassName("org.postgresql.Driver")
				.build();

		// adil chisel database server connect
		/*
		 * DataSource ds =
		 * dsb.username("YHCInU1PcqQvV_iM").password("dUZOWvu43fK7NTA7")
		 * .url("jdbc:postgresql://localhost:30015/Q1WVjEu8nxqbBQ3V")
		 * .driverClassName("org.postgresql.Driver").build();
		 */

		// Jameela chisel database server connect
		// DataSource ds =
		// dsb.username("cAehgkdFIdC-0nJv").password("Cp1HNwyy6Q_isbj3")
		// .url("jdbc:postgresql://localhost:30015/guddVbgkSIZsAM7O").driverClassName("org.postgresql.Driver")
		// .build();

		return ds;

	}

	/**
	 * Declare the JPA entity manager factory.
	 * 
	 * @throws SQLException
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setDataSource(ds);

		// Classpath scanning of @Component, @Service, etc annotated class
		entityManagerFactory.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));

		// Vendor adapter
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		// Hibernate properties
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		additionalProperties.put("hibernate.jdbc.lob.non_contextual_creation",
				env.getProperty("hibernate.jdbc.lob.non_contextual_creation"));
		additionalProperties.put("hibernate.temp.use_jdbc_metadata_defaults",
				env.getProperty("hibernate.temp.use_jdbc_metadata_defaults"));

		entityManagerFactory.setJpaProperties(additionalProperties);

		return entityManagerFactory;
	}

	/**
	 * Declare the transaction manager.
	 */
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;
	}

	/**
	 * PersistenceExceptionTranslationPostProcessor is a bean post processor
	 * which adds an advisor to any bean annotated with Repository so that any
	 * platform-specific exceptions are caught and then rethrown as one Spring's
	 * unchecked data access exceptions (i.e. a subclass of
	 * DataAccessException).
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// Private fields

	@Autowired
	private Environment env;

	@Autowired
	private DataSource ds;

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

}
