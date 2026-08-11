package com.treinamento.integrations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.treinamento.integrations.services.EmailService;
import com.treinamento.integrations.services.SendGridEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Bean
	public EmailService emailService() {
		return new SendGridEmailService();
	}

}
