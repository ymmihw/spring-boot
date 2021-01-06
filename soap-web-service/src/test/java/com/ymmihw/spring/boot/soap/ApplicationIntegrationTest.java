package com.ymmihw.spring.boot.soap;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import com.ymmihw.spring.boot.soap.gen.GetCountryRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTest {

  private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

  @LocalServerPort
  private int port = 0;

  @BeforeEach
  public void init() throws Exception {
    marshaller.setPackagesToScan(ClassUtils.getPackageName(GetCountryRequest.class));
    marshaller.afterPropertiesSet();
  }

  @Test
  public void whenSendRequest_thenResponseIsNotNull() {
    WebServiceTemplate ws = new WebServiceTemplate(marshaller);
    GetCountryRequest request = new GetCountryRequest();
    request.setName("Spain");

    assertThat(ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request)).isNotNull();
  }
}
