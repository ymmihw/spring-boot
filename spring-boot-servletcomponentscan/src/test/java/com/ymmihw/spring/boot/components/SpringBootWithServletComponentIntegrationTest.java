package com.ymmihw.spring.boot.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import com.ymmihw.spring.boot.SpringBootAnnotatedApp;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = SpringBootAnnotatedApp.class)
@TestPropertySource(properties = {"security.basic.enabled=false"})
public class SpringBootWithServletComponentIntegrationTest {

  @Autowired
  private ServletContext servletContext;

  @Test
  public void givenServletContext_whenAccessAttrs_thenFoundAttrsPutInServletListner() {
    assertNotNull(servletContext);
    assertNotNull(servletContext.getAttribute("servlet-context-attr"));
    assertEquals("test", servletContext.getAttribute("servlet-context-attr"));
  }

  @Test
  public void givenServletContext_whenCheckHelloFilterMappings_thenCorrect() {
    assertNotNull(servletContext);
    FilterRegistration filterRegistration = servletContext.getFilterRegistration("hello filter");

    assertNotNull(filterRegistration);
    assertTrue(filterRegistration.getServletNameMappings().contains("echo servlet"));
  }

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void givenServletFilter_whenGetHello_thenRequestFiltered() {
    ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/hello", String.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("filtering hello", responseEntity.getBody());
  }

  @Test
  public void givenFilterAndServlet_whenPostEcho_thenEchoFiltered() {
    ResponseEntity<String> responseEntity =
        this.restTemplate.postForEntity("/echo", "echo", String.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("filtering echo", responseEntity.getBody());
  }

}
