package com.em.test_em;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/** The main entry point class for the EmTest application. */
@SpringBootApplication
@OpenAPIDefinition
@ComponentScan(
    basePackages = {
      "com.em.test_em.config",
      "com.em.test_em.controllers",
      "com.em.test_em.beans",
      "com.em.test_em.services",
      "com.em.test_em.repositories"
    })
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class EmTestApplication {

  /**
   * The main method to start the EmTest application.
   *
   * @param args The command-line arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(EmTestApplication.class, args);
  }

  /**
   * Bean definition for ModelMapper, a mapping library to simplify object-to-object mapping.
   *
   * @return A new instance of ModelMapper.
   */
  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
