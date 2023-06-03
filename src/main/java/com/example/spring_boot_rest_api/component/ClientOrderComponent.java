package com.example.spring_boot_rest_api.component;

import com.example.spring_boot_rest_api.service.IDishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientOrderComponent {

	private static final Logger logger = LoggerFactory.getLogger(ClientOrderComponent.class);

	public ClientOrderComponent(/*@Qualifier("fishDishService")*/ IDishService dishService) {
		logger.debug("Prepare {}", dishService.getName());
	}

	public void securedMethod() {
		logger.debug("secured method execution");
	}
}
