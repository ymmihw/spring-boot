package com.ymmihw.spring.boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

  @GetMapping("/welcome")
  public String welcome() {
    return "ssl/welcome";
  }

}