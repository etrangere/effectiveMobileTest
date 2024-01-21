package com.em.test_em.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Configuration class for Cross-Origin Resource Sharing (CORS) in the EmTest application. */
@Configuration
@Order(2)
public class CorsConfig implements WebMvcConfigurer {

  /**
   * Configure CORS mappings to allow specific origins and methods.
   *
   * @param registry The CORS registry to configure.
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins(
            "http://localhost:8086",
            "http://localhost:8085",
            "http://localhost:8084") // frontend URL
        .allowedMethods("GET", "POST", "PUT", "DELETE");
  }
}
