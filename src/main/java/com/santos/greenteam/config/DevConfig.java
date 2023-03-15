package com.santos.greenteam.config;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.santos.greenteam.service.DBservice;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Autowired
	private DBservice dBservice;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dBservice.instantiateTestDatabase();
		return true;
		
	}
	
}
