package com.ymmihw.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.ymmihw.spring.boot.components")
public class SpringBootAnnotatedApp {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootAnnotatedApp.class, args);
  }
}
