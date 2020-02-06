package com.ymmihw.spring.boot.actuator;

import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

  @GetMapping(value = "/greeting")
  @ResponseBody
  public String getGreeting(Principal user) {
    return "Good morning " + user.getName();
  }

  @PostMapping(value = "/greeting")
  @ResponseBody
  public String echo(@RequestBody User user) {
    return "echoing " + user.getName();
  }
}
