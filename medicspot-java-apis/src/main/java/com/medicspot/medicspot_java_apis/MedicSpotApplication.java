package com.medicspot.medicspot_java_apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.medicspot.*")
@EnableJpaRepositories
public class MedicSpotApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(MedicSpotApplication.class, args);
    }
}
