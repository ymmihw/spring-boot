package com.ymmihw.spring.boot;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ConfigPropertiesDemoApplication.class)
@TestPropertySource("classpath:configprops-test.properties")
public class ConfigPropertiesIntegrationTest {

  @Autowired
  private ConfigProperties properties;

  @Test
  public void whenSimplePropertyQueriedthenReturnsProperty() throws Exception {
    assertTrue(properties.getFrom() != null, "From address is read as null!");
  }

  @Test
  public void whenListPropertyQueriedthenReturnsProperty() throws Exception {
    assertTrue(properties.getDefaultRecipients().size() == 2, "Couldn't bind list property!");
    assertTrue(properties.getDefaultRecipients().size() == 2,
        "Incorrectly bound list property. Expected 2 entries!");
  }

  @Test
  public void whenMapPropertyQueriedthenReturnsProperty() throws Exception {
    assertTrue(properties.getAdditionalHeaders() != null, "Couldn't bind map property!");
    assertTrue(properties.getAdditionalHeaders().size() == 3,
        "Incorrectly bound map property. Expected 3 Entries!");
  }

  @Test
  public void whenObjectPropertyQueriedthenReturnsProperty() throws Exception {
    assertTrue(properties.getCredentials() != null, "Couldn't bind map property!");
    assertTrue(properties.getCredentials().getAuthMethod().equals("SHA1"),
        "Incorrectly bound object property!");
    assertTrue(properties.getCredentials().getUsername().equals("john"),
        "Incorrectly bound object property!");
    assertTrue(properties.getCredentials().getPassword().equals("password"),
        "Incorrectly bound object property!");
  }
}
