package com.ymmihw.spring.boot.basicauth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringBootSecurityApplication.class)
public class BasicAuthConfigurationIntegrationTest {

  TestRestTemplate restTemplate;
  URL base;

  @LocalServerPort
  int port;

  @BeforeEach
  public void setUp() throws MalformedURLException {
    restTemplate = new TestRestTemplate("user", "password");
    base = new URL("http://localhost:" + port);
  }

  @Test
  public void whenLoggedUserRequestsHomePage_ThenSuccess()
      throws IllegalStateException, IOException {
    ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().contains("Baeldung"));
  }

  @Test
  public void whenUserWithWrongCredentialsRequestsHomePage_ThenUnauthorizedPage()
      throws IllegalStateException, IOException {
    restTemplate = new TestRestTemplate("user", "wrongpassword");
    ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
  }
}
