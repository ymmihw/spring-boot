package com.ymmihw.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FailureAnalyzerApplication {
  public static void main(String[] args) {
    System.setProperty("security.basic.enabled", "false");
    SpringApplication.run(FailureAnalyzerApplication.class, args);
  }
}
