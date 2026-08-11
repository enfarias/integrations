package com.treinamento.integrations.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.treinamento.integrations.dto.EmailDTO;

public class MockEmailService implements EmailService {
	
	private static Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	public void sendEmail(EmailDTO dto) {
			LOG.info("Sending email to: " + dto.getReplyTo());
			LOG.info("Email sent!");	

	}
}
