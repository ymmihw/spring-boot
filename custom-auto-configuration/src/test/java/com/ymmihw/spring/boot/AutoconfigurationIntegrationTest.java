package com.ymmihw.spring.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest(classes = AutoconfigurationApplication.class)
@EnableJpaRepositories(basePackages = {"com.ymmihw.spring.boot"})
public class AutoconfigurationIntegrationTest {

  @Autowired
  private MyUserRepository userRepository;

  @Test
  public void whenSaveUser_thenOk() {
    MyUser user = new MyUser("user@email.com");
    userRepository.save(user);
  }

}
