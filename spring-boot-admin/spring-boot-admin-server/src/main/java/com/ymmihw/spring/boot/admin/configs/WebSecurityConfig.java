package com.ymmihw.spring.boot.admin.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final String adminContextPath;

  public WebSecurityConfig(AdminServerProperties adminServerProperties) {
    this.adminContextPath = adminServerProperties.getContextPath();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
      SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
      successHandler.setTargetUrlParameter("redirectTo");
      successHandler.setDefaultTargetUrl(adminContextPath + "/");

      http.authorizeRequests()
          .antMatchers(adminContextPath + "/assets/**").permitAll() // <1>
          .antMatchers(adminContextPath + "/login").permitAll()
          .antMatchers(adminContextPath + "/actuator/env").authenticated()
          .anyRequest().authenticated() // <2>
          .and()
      .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and() // <3>
      .logout().logoutUrl(adminContextPath + "/logout").and()
      .httpBasic().and() // <4>
      .csrf()
          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())  // <5>
          .ignoringAntMatchers(
              adminContextPath + "/instances",   // <6>
              adminContextPath + "/actuator/**"  // <7>
          );
      // @formatter:on
  }
}
