package com.ymmihw.spring.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = GreeterSampleApplication.class)
public class GreeterSampleApplicationIntegrationTest {

  @Autowired
  private Greeter greeter;

  @Test
  public void givenMorningTime_ifMorningMessage_thenSuccess() {
    String expected = "Hello Ymmihw, Good Morning";
    String actual = greeter.greet(LocalDateTime.of(2017, 3, 1, 6, 0));
    assertEquals(expected, actual);
  }

  @Test
  public void givenAfternoonTime_ifAfternoonMessage_thenSuccess() {
    String expected = "Hello Ymmihw, Woha Afternoon";
    String actual = greeter.greet(LocalDateTime.of(2017, 3, 1, 13, 0));
    assertEquals(expected, actual);
  }

  @Test
  public void givenEveningTime_ifEveningMessage_thenSuccess() {
    String expected = "Hello Ymmihw, Good Evening";
    String actual = greeter.greet(LocalDateTime.of(2017, 3, 1, 19, 0));
    assertEquals(expected, actual);
  }

  @Test
  public void givenNightTime_ifNightMessage_thenSuccess() {
    String expected = "Hello Ymmihw, Good Night";
    String actual = greeter.greet(LocalDateTime.of(2017, 3, 1, 21, 0));
    assertEquals(expected, actual);
  }

}
