package com.ymmihw.spring.boot.actuator.trace;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentTrace {

  private HttpTrace httpTrace;

  private String requestBody;

  private String responseBody;

  private Authentication principal;
}
