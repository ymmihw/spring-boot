package com.ymmihw.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ExitApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx =
        new SpringApplicationBuilder(ShutdownEndpointApplication.class).web(WebApplicationType.NONE)
            .run();

    int exitCode = SpringApplication.exit(ctx, () -> {
      return 0;
    });

    System.out.println("Exit Spring Boot");

    System.exit(exitCode);
  }
}
