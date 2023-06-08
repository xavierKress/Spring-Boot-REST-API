package com.example.spring_boot_rest_api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenAPIConfig {

	@Value("http://localhost:8081/")
	private String devUrl;

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("Server URL in Development environment");


		Contact contact = new Contact();
		contact.setEmail("xkress@mediametrie.fr");
		contact.setName("Xavier KRESS");
		contact.setUrl("https://www.mediametrie.fr");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info()
				.title("Book Management API")
				.version("1.0")
				.contact(contact)
				.description("This API exposes endpoints to manage nooks.").termsOfService("https://www.mediametrie.fr")
				.license(mitLicense);

		return new OpenAPI().info(info).servers(List.of(devServer));
	}
}

