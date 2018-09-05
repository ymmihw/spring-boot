package com.ymmihw.spring.boot.ctx1;

import org.springframework.stereotype.Service;
import com.ymmihw.spring.boot.parent.IHomeService;

@Service
public class GreetingService implements IHomeService {

  @Override
  public String getGreeting() {
    return "Greetings for the day";
  }
}
