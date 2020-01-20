package com.ymmihw.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  // @Controller
  static class FaviconController {
    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {}
  }

  @RestController
  static class MyController {
    @GetMapping("test")
    @ResponseBody
    String returnNoFavicon() {
      return "test";
    }
  }
}
