package com.ymmihw.spring.boot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ymmihw.spring.boot.components")
public class SpringBootPlainApp {
  public static void main(String[] args) {}
}
