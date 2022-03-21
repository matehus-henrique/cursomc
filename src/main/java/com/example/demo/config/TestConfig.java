package com.example.demo.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.service.DBService;
import com.example.demo.service.EmailService;
import com.example.demo.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dBService;
	
	

	@Bean
	public boolean instantiateDataBase() throws ParseException {

		dBService.instantiateTestDatabase();

		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	} 
}
