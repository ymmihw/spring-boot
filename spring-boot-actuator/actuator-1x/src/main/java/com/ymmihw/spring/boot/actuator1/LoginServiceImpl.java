package com.ymmihw.spring.boot.actuator1;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginServiceImpl {

  private final CounterService counterService;

  public LoginServiceImpl(CounterService counterService) {
    this.counterService = counterService;
  }


  @RequestMapping("/aa")
  public boolean login(String userName, char[] password) {
    boolean success;
    if (userName.equals("1") && "1".toCharArray().equals(password)) {
      counterService.increment("counter.login.success");
      success = true;
    } else {
      counterService.increment("counter.login.failure");
      success = false;
    }
    return success;
  }
}
