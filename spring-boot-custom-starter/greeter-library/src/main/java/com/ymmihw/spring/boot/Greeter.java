package com.ymmihw.spring.boot;

import static com.ymmihw.spring.boot.GreeterConfigParams.AFTERNOON_MESSAGE;
import static com.ymmihw.spring.boot.GreeterConfigParams.EVENING_MESSAGE;
import static com.ymmihw.spring.boot.GreeterConfigParams.MORNING_MESSAGE;
import static com.ymmihw.spring.boot.GreeterConfigParams.NIGHT_MESSAGE;
import static com.ymmihw.spring.boot.GreeterConfigParams.USER_NAME;
import java.time.LocalDateTime;

public class Greeter {

  private GreetingConfig greetingConfig;

  public Greeter(GreetingConfig greetingConfig) {
    this.greetingConfig = greetingConfig;
  }

  public String greet(LocalDateTime localDateTime) {

    String name = greetingConfig.getProperty(USER_NAME);
    int hourOfDay = localDateTime.getHour();

    String format = "Hello %s, %s";
    if (hourOfDay >= 5 && hourOfDay < 12) {
      return String.format(format, name, greetingConfig.get(MORNING_MESSAGE));
    } else if (hourOfDay >= 12 && hourOfDay < 17) {
      return String.format(format, name, greetingConfig.get(AFTERNOON_MESSAGE));
    } else if (hourOfDay >= 17 && hourOfDay < 20) {
      return String.format(format, name, greetingConfig.get(EVENING_MESSAGE));
    } else {
      return String.format(format, name, greetingConfig.get(NIGHT_MESSAGE));
    }
  }

  public String greet() {
    return greet(LocalDateTime.now());
  }

}
