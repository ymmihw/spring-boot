package com.ymmihw.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@SpringBootApplication
public class XxlJobExecutorApplication {

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(XxlJobExecutorApplication.class, args);
    TimeUnit.DAYS.sleep(1);
  }
}
