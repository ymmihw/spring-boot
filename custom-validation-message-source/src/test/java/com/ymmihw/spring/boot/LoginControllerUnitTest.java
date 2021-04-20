package com.ymmihw.spring.boot;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.ymmihw.spring.boot.config.CustomMessageSourceConfiguration;

@SpringBootTest
@WebMvcTest(value = LoginController.class)
@ContextConfiguration(
    classes = {SpringBootMvcApplication.class, CustomMessageSourceConfiguration.class})
public class LoginControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void givenLoginForm_whenEmailFieldNotProvided_testCustomValidMessageIsReturned()
      throws Exception {

    RequestBuilder builder =
        MockMvcRequestBuilders.post("/loginform").param("email", "").param("password", "helo");

    MvcResult perform = mockMvc.perform(builder).andReturn();
    assertTrue(perform.getResolvedException().getMessage().contains("valid email"));

  }

  @Test
  public void givenLoginForm_whenEmailFieldNotProvided_testCustomFrValidMessageIsReturned()
      throws Exception {

    RequestBuilder builder = MockMvcRequestBuilders.post("/loginform").param("email", "")
        .param("password", "helo").header("accept-language", "fr");
    MvcResult perform = mockMvc.perform(builder).andReturn();
    assertTrue(perform.getResolvedException().getMessage().contains("fournir un"));

  }
}
