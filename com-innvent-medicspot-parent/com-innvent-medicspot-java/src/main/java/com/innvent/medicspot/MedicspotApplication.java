package com.innvent.medicspot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.innvent.medicspot.*")
@EnableJpaRepositories
@EnableCaching
@EnableScheduling
public class MedicspotApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicspotApplication.class, args);
	}
}
