package com.em.test_em.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configuration class for customizing OpenAPI documentation. */
@Configuration
public class OpenAPIConfig {
  @Value("${gourgen.openapi.dev-url}")
  private String devUrl;

  @Value("${gourgen.openapi.prod-url}")
  private String prodUrl;

  @Value("${gourgen.openapi.test-url}")
  private String testUrl;

  /**
   * Configures the OpenAPI documentation for the Task Management System Service API.
   *
   * @return An instance of OpenAPI with the configured information.
   */
  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Server URL in Production environment");

    Server testServer = new Server();
    testServer.setUrl(testUrl);
    testServer.setDescription("Server URL in Test environment");

    Contact contact = new Contact();
    contact.setEmail("g.khachatrian@yahoo.com");
    contact.setName("Gourgen Khachatrian");
    contact.setUrl("https://portfolio.gourgen-khachatrian.fr/");

    License mitLicense =
        new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info =
        new Info()
            .title("Effective Mobile - Task Management System Service API")
            .version("1.0")
            .contact(contact)
            .description("This API exposes endpoints to manage test service.")
            .termsOfService("https://portfolio.gourgen-khachatrian.fr/")
            .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer, prodServer, testServer));
  }
}
