package com.example.spring_boot_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRestApiApplication {

	//private static final Logger logger = LoggerFactory.getLogger(SpringBootRestApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApplication.class, args);
		//IDishService dishService = context.getBean(IDishService.class);
		//logger.debug("meal to serve : {}", dishService.getName());
	}

}
