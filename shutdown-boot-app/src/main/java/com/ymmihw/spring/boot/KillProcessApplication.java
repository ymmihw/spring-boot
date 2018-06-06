package com.ymmihw.spring.boot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class KillProcessApplication {

  public static void main(String[] args) {
    SpringApplicationBuilder app =
        new SpringApplicationBuilder(KillProcessApplication.class).web(WebApplicationType.SERVLET);
    app.build().addListeners(new ApplicationPidFileWriter("./shutdown.pid"));
    app.run();
  }
}
