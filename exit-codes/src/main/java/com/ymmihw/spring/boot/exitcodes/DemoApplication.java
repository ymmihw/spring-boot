package com.ymmihw.spring.boot.exitcodes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

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

  @Bean
  CommandLineRunner createException() {
    return args -> Integer.parseInt("test");
  }

  @Bean
  ExitCodeExceptionMapper exitCodeToexceptionMapper() {
    return exception -> {
      if (exception.getCause() instanceof NumberFormatException) {
        return 80;
      }
      return 1;
    };
  }

  @Bean
  DemoListener demoListenerBean() {
    return new DemoListener();
  }

  private static class DemoListener {
    @EventListener
    public void exitEvent(ExitCodeEvent event) {
      System.out.println("Exit code: " + event.getExitCode());
    }
  }
}
