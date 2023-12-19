package com.em.test_em;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;



@SpringBootApplication(scanBasePackages = {"config"})
@OpenAPIDefinition
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class EmTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmTestApplication.class, args);
	}

}
