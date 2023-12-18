package com.em.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication

//remove after test
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class EmTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmTestApplication.class, args);
	}

}
