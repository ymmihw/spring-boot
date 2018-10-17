package com.ymmihw.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping(path = "/foos", produces = {MediaType.APPLICATION_JSON_VALUE})
  public String foos() {
    return "foos";
  }

  @GetMapping("/foos/{id}")
  public String foos(String id) {
    return id;
  }
}
