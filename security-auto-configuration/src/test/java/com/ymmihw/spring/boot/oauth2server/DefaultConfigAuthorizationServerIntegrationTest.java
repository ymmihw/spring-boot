package com.ymmihw.spring.boot.oauth2server;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@SpringBootTest(webEnvironment = RANDOM_PORT,
    classes = SpringBootAuthorizationServerApplication.class, properties = {
        "security.oauth2.client.client-id=client", "security.oauth2.client.client-secret=baeldung"})
public class DefaultConfigAuthorizationServerIntegrationTest extends OAuth2IntegrationTestSupport {

  @Test
  public void givenOAuth2Context_whenAccessTokenIsRequested_ThenAccessTokenValueIsNotNull() {
    ClientCredentialsResourceDetails resourceDetails =
        getClientCredentialsResourceDetails("client", asList("read", "write"));
    OAuth2RestTemplate restTemplate = getOAuth2RestTemplate(resourceDetails);

    OAuth2AccessToken accessToken = restTemplate.getAccessToken();

    assertNotNull(accessToken);

  }

}

