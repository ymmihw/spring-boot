package com.ymmihw.spring.boot.actuator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("echo")
public class EchoController {

  @GetMapping
  public String echo(@RequestParam("msg") String msg) {
    return "echoing " + msg;
  }
  
  @PostMapping
  public String echo(@RequestBody User user) {
    return "echoing " + user.getName();
  }

}
