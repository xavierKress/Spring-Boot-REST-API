package com.example.spring_boot_rest_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	public EmailService() {
		logger.debug("Email Service instanciated !");
	}

	public void processMessage(String message) {
		logger.debug("process message {}", message);
	}
}
