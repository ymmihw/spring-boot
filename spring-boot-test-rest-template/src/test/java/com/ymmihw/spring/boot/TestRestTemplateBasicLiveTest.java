package com.ymmihw.spring.boot;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import okhttp3.Request;
import okhttp3.RequestBody;

@SpringBootTest(classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplateBasicLiveTest {
  private static final String URL_SECURED_BY_AUTHENTICATION =
      "http://httpbin.org/basic-auth/user/passwd";
  @LocalServerPort
  private int port;

  private RestTemplateBuilder restTemplateBuilder;
  private String fooResourceUrl;
  private String baseUrl;

  @BeforeEach
  public void beforeTest() {
    restTemplateBuilder = new RestTemplateBuilder();
    fooResourceUrl = "http://localhost:" + port + "/spring-rest/foos";
    baseUrl = "http://localhost:" + port + "/spring-rest";
  }

  // GET
  @Test
  public void givenTestRestTemplate_whenSendGetForEntity_thenStatusOk() {
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    ResponseEntity<String> response =
        testRestTemplate.getForEntity(fooResourceUrl + "/1", String.class);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  @Test
  public void givenRestTemplateWrapper_whenSendGetForEntity_thenStatusOk() {
    TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    ResponseEntity<String> response =
        testRestTemplate.getForEntity(fooResourceUrl + "/1", String.class);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  @Test
  public void givenRestTemplateBuilderWrapper_whenSendGetForEntity_thenStatusOk() {
    TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    ResponseEntity<String> response =
        testRestTemplate.getForEntity(fooResourceUrl + "/1", String.class);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  @Test
  public void givenRestTemplateWrapperWithCredentials_whenSendGetForEntity_thenStatusOk() {
    TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplateBuilder, "user", "passwd");
    ResponseEntity<String> response =
        testRestTemplate.getForEntity(URL_SECURED_BY_AUTHENTICATION, String.class);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  @Test
  public void givenTestRestTemplateWithCredentials_whenSendGetForEntity_thenStatusOk() {
    TestRestTemplate testRestTemplate = new TestRestTemplate("user", "passwd");
    ResponseEntity<String> response =
        testRestTemplate.getForEntity(URL_SECURED_BY_AUTHENTICATION, String.class);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  @Test
  public void givenTestRestTemplateWithBasicAuth_whenSendGetForEntity_thenStatusOk() {
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    ResponseEntity<String> response = testRestTemplate.withBasicAuth("user", "passwd")
        .getForEntity(URL_SECURED_BY_AUTHENTICATION, String.class);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  @Test
  public void givenTestRestTemplateWithCredentialsAndEnabledCookies_whenSendGetForEntity_thenStatusOk() {
    TestRestTemplate testRestTemplate =
        new TestRestTemplate("user", "passwd", TestRestTemplate.HttpClientOption.ENABLE_COOKIES);
    ResponseEntity<String> response =
        testRestTemplate.getForEntity(URL_SECURED_BY_AUTHENTICATION, String.class);
    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
  }

  // HEAD
  @Test
  public void givenFooService_whenCallHeadForHeaders_thenReceiveAllHeaders() {
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    final HttpHeaders httpHeaders = testRestTemplate.headForHeaders(fooResourceUrl);
    assertTrue(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON));
  }

  // POST
  @Test
  public void givenService_whenPostForObject_thenCreatedObjectIsReturned() {
    TestRestTemplate testRestTemplate = new TestRestTemplate("user", "passwd");
    final RequestBody body = RequestBody.create(okhttp3.MediaType.parse("text/html; charset=utf-8"),
        "{\"id\":1,\"name\":\"Jim\"}");
    final Request request = new Request.Builder().url(baseUrl + "/users/detail").post(body).build();
    testRestTemplate.postForObject(URL_SECURED_BY_AUTHENTICATION, request, String.class);
  }

  // PUT
  @Test
  public void givenService_whenPutForObject_thenCreatedObjectIsReturned() {
    TestRestTemplate testRestTemplate = new TestRestTemplate("user", "passwd");
    final RequestBody body = RequestBody.create(okhttp3.MediaType.parse("text/html; charset=utf-8"),
        "{\"id\":1,\"name\":\"Jim\"}");
    final Request request = new Request.Builder().url(baseUrl + "/users/detail").post(body).build();
    testRestTemplate.put(URL_SECURED_BY_AUTHENTICATION, request, String.class);
  }

}
