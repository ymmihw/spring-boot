package com.ymmihw.spring.boot.admin;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import javax.servlet.Filter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {SpringBootAdminServerApplication.class})
public class WebSecurityConfigIntegrationTest {

  @Autowired
  WebApplicationContext wac;

  @Autowired
  private Filter springSecurityFilterChain;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilters(springSecurityFilterChain).build();
  }

  @Test
  public void whenApplicationStarts_ThenGetLoginPageWithSuccess() throws Exception {
    mockMvc.perform(get("/login.html")).andExpect(status().is2xxSuccessful());
  }

  @Test
  public void whenFormLoginAttempted_ThenSuccess() throws Exception {
    mockMvc.perform(formLogin("/login").user("admin").password("admin"));
  }

  @Test
  public void whenFormLoginWithSuccess_ThenApiEndpointsAreAccessible() throws Exception {
    mockMvc.perform(get("/applications").with(user("admin").password("admin")))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void whenHttpBasicAttempted_ThenSuccess() throws Exception {
    mockMvc.perform(get("/applications").with(httpBasic("admin", "admin")))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void whenInvalidHttpBasicAttempted_ThenUnauthorized() throws Exception {
    mockMvc.perform(get("/applications").with(httpBasic("admin", "invalid")))
        .andExpect(status().isUnauthorized());
  }

}
