package com.example.spring_boot_rest_api;

import com.example.spring_boot_rest_api.service.IDishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootRestApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootRestApiApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootRestApiApplication.class, args);

		IDishService dishService = context.getBean(IDishService.class);
		logger.debug("meal to serve : {}", dishService.getName());
	}

}
