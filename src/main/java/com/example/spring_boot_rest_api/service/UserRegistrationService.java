package com.example.spring_boot_rest_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationService.class);
	EmailService emailService;

	public UserRegistrationService(EmailService emailService) {
		logger.debug("User registration service instanciated !");
		this.emailService = emailService;
	}

	public void sendMail() {
		emailService.processMessage("user registered");
	}
}
