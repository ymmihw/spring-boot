package com.ymmihw.spring.boot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CloseContextApplication {
  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = new SpringApplicationBuilder(CloseContextApplication.class)
        .web(WebApplicationType.NONE).run();
    System.out.println("Spring Boot application started");
    ctx.getBean(TerminateBean.class);
    ctx.close();
  }
}
