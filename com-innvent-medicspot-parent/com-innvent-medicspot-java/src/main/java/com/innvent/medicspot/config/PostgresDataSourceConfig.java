package com.innvent.medicspot.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresDataSourceConfig {

	@Bean
	DataSource getDataSource() throws SQLException {
		
		DataSourceBuilder dsb = DataSourceBuilder.create();
		//Adil cf database
				/*DataSource ds = dsb.username("YHCInU1PcqQvV_iM").password("dUZOWvu43fK7NTA7")
						.url("jdbc:postgresql://10.11.241.47:42888/Q1WVjEu8nxqbBQ3V")
						.driverClassName("org.postgresql.Driver").build();*/
				
				//Himanshu cf DataSource connect
						/*DataSource ds = dsb.username("CuLy38hjO9l0hezP").password("q5_d29iSwQ2Q7uuW")
								.url("jdbc:postgresql://10.11.241.24:43241/P11OU0gucj2U7kiY")
								.driverClassName("org.postgresql.Driver").build();*/
				
				//Jameela cf DataSource connect
						/*DataSource ds = dsb.username("cAehgkdFIdC-0nJv").password("Cp1HNwyy6Q_isbj3")
								.url("jdbc:postgresql://10.11.241.0:59486/guddVbgkSIZsAM7O").driverClassName("org.postgresql.Driver")
								.build();*/ 
				
				//adil chisel database server connect
				/*DataSource ds = dsb.username("YHCInU1PcqQvV_iM").password("dUZOWvu43fK7NTA7")
						.url("jdbc:postgresql://localhost:30015/Q1WVjEu8nxqbBQ3V")
						.driverClassName("org.postgresql.Driver").build();*/
				
				//Jameela chisel database server connect
				DataSource ds = dsb.username("cAehgkdFIdC-0nJv").password("Cp1HNwyy6Q_isbj3")
								.url("jdbc:postgresql://localhost:30015/guddVbgkSIZsAM7O").driverClassName("org.postgresql.Driver")
								.build(); 

		return ds;

	}
}
