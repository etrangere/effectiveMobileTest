package com.em.test_em;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;


@SpringBootApplication
@OpenAPIDefinition
@ComponentScan(basePackages = {"com.em.test_em.config","com.em.test_em.controllers","com.em.test_em.beans","com.em.test_em.services","com.em.test_em.repositories"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class EmTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmTestApplication.class, args);
	}

}
