package com.ymmihw.spring.boot.basicauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class
    // ,ManagementWebSecurityAutoConfiguration.class
}, scanBasePackages = "com.ymmihw.spring.boot.basicauth")
public class SpringBootSecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityApplication.class, args);
  }
}
