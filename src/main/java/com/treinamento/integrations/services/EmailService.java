package com.treinamento.integrations.services;

import com.treinamento.integrations.dto.EmailDTO;

public interface EmailService {
	
	void sendEmail(EmailDTO dto);

}
