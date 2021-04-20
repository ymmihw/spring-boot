package com.ymmihw.spring.boot.admin;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import com.ymmihw.spring.boot.admin.configs.HazelcastConfig;

@SpringBootTest(classes = {HazelcastConfig.class}, webEnvironment = NONE)
public class HazelcastConfigIntegrationTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void whenApplicationContextStarts_HazelcastConfigBeanExists() {
    assertNotEquals(applicationContext.getBean("hazelcast"), null);
  }
}
