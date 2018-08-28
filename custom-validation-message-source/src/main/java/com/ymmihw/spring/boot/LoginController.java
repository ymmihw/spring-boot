package com.ymmihw.spring.boot;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ymmihw.spring.boot.model.LoginForm;

@RestController
@RequestMapping("/")
public class LoginController {

  @PostMapping("loginform")
  public String processLogin(@Valid LoginForm form) {
    return "Success";
  }
}
