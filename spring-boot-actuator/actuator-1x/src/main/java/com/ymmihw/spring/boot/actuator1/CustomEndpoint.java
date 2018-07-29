package com.ymmihw.spring.boot.actuator1;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

@Component
public class CustomEndpoint implements Endpoint<List<String>> {

  @Override
  public String getId() {
    return "customEndpoint";
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean isSensitive() {
    return true;
  }

  @Override
  public List<String> invoke() {
    // Custom logic to build the output
    List<String> messages = new ArrayList<String>();
    messages.add("This is message 1");
    messages.add("This is message 2");
    return messages;
  }
}
