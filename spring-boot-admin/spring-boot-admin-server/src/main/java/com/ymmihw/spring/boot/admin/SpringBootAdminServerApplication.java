package com.ymmihw.spring.boot.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.web.client.InstanceExchangeFilterFunction;

@SpringBootApplication
@EnableAdminServer
public class SpringBootAdminServerApplication {
  private static final Logger log = LoggerFactory.getLogger(SpringBootAdminServerApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(SpringBootAdminServerApplication.class, args);
  }

  @Bean
  public InstanceExchangeFilterFunction auditLog() {
    return (instance, request, next) -> {
      if (HttpMethod.DELETE.equals(request.method()) || HttpMethod.POST.equals(request.method())) {
        log.info("{} for {} on {}", request.method(), instance.getId(), request.url());
      }
      return next.exchange(request);
    };
  }
}
