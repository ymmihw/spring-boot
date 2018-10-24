package com.ymmihw.spring.boot;

import static org.junit.Assert.assertEquals;
import java.util.Collections;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HttpsApplicationIntegrationTest {

  private static final String WELCOME_URL = "https://192.168.10.108:8443/welcome";

  @Value("${trust.store}")
  private Resource trustStore;

  @Value("${trust.store.password}")
  private String trustStorePassword;

  @Test
  public void whenGETanHTTPSResource_thenCorrectResponse() throws Exception {
    ResponseEntity<String> response =
        restTemplate().getForEntity(WELCOME_URL, String.class, Collections.emptyMap());

    assertEquals("<h1>Welcome to Secured Site</h1>", response.getBody());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  RestTemplate restTemplate() throws Exception {
    SSLContext sslContext = new SSLContextBuilder()
        .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray()).build();
    SSLConnectionSocketFactory socketFactory =
        new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
          @Override
          public boolean verify(String hostname, SSLSession session) {
            return true;
          }
        });
    HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
    HttpComponentsClientHttpRequestFactory factory =
        new HttpComponentsClientHttpRequestFactory(httpClient);

    return new RestTemplate(factory);
  }
}
