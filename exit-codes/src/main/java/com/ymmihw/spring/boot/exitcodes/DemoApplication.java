package com.ymmihw.spring.boot.exitcodes;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication implements ExitCodeGenerator {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
    int exit = SpringApplication.exit(ctx);
    System.out.println(exit);
    System.exit(exit);
  }

  @Override
  public int getExitCode() {
    return 42;
  }
}
