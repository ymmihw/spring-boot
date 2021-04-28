package com.ymmihw.spring.boot;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.ymmihw.spring.boot.SqlLoggingTest.TestConfig;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class SqlLoggingTest {

  @EnableJpaRepositories("com.ymmihw.spring.boot")
  @EntityScan("com.ymmihw.spring.boot")
  @EnableAutoConfiguration
  static class TestConfig {
  }

  @Autowired
  private FooRepository fooRepository;

  @Test
  public void givenFooRepository_whenSave_thenOK() {
    fooRepository.save(new Foo());
  }

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  public void givenJdbcTemplate_whenSave_thenOK() {
    jdbcTemplate.update("insert into foo (id, name) values (null, ?)", "abc");
  }

  @Autowired
  EntityManager entityManager;

  @Test
  @Transactional
  public void givenEntityManager_whenSave_thenOK() {
    entityManager.persist(new Foo());
  }
}
