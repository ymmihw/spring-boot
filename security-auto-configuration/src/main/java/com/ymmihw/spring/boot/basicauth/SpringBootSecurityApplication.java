package com.ymmihw.spring.boot.basicauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {/*SecurityAutoConfiguration.class*/},
    scanBasePackages = "com.ymmihw.spring.boot.basicauth")
public class SpringBootSecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityApplication.class, args);
  }
}
