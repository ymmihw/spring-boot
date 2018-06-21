package com.ymmihw.spring.boot.oauth2server;

import java.security.Principal;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAuthorizationServer
@SpringBootApplication(scanBasePackages = "com.ymmihw.spring.boot.oauth2server")
public class SpringBootAuthorizationServerApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder().profiles("authz")
        .sources(SpringBootAuthorizationServerApplication.class).build().run(args);
  }

  @RestController
  class UserController {

    @GetMapping("/user")
    public Principal user(Principal user) {
      return user;
    }

  }
}
