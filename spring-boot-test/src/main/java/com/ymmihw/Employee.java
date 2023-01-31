package com.ymmihw;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "person")
@Data
public class Employee {

  public Employee() {}

  public Employee(String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Size(min = 3, max = 20)
  private String name;

  private Date birthday;
}
