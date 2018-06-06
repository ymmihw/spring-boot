package com.ymmihw.spring.boot;

import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class TerminateBean {

  @PreDestroy
  public void onDestroy() throws Exception {
    System.out.println("Spring Container is destroyed!");
  }
}
