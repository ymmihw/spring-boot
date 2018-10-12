package com.ymmihw.spring.boot;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MyUser {
  @Id
  private String email;

  public MyUser() {}

  public MyUser(String email) {
    super();
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
