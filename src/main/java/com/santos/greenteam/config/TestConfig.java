package com.santos.greenteam.config;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.santos.greenteam.service.DBservice;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBservice dBservice;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		dBservice.instantiateTestDatabase();
		return true;
	}
	
}
