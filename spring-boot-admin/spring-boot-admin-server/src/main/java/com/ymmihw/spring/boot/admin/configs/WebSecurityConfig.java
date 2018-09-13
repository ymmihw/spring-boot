package com.ymmihw.spring.boot.admin.configs;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import de.codecentric.boot.admin.server.config.AdminServerProperties;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final String adminContextPath;

  public WebSecurityConfig(AdminServerProperties adminServerProperties) {
    this.adminContextPath = adminServerProperties.getContextPath();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    SavedRequestAwareAuthenticationSuccessHandler successHandler =
        new SavedRequestAwareAuthenticationSuccessHandler();
    successHandler.setTargetUrlParameter("redirectTo");
    successHandler.setDefaultTargetUrl("/");

    // @formatter:off
    http.authorizeRequests()
        .antMatchers(adminContextPath + "/assets/**").permitAll() 
        .antMatchers(adminContextPath + "/login").permitAll()
        .antMatchers(adminContextPath + "/logout").permitAll()
        .antMatchers(adminContextPath + "/login.html").permitAll()
        .antMatchers(adminContextPath + "/actuator/env").authenticated()
        .anyRequest().authenticated() 
        .and()
    .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
    .logout().logoutUrl(adminContextPath + "/logout").and()
    .httpBasic().and() 
    .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())  
        .ignoringAntMatchers(
            adminContextPath + "/instances",   
            adminContextPath + "/actuator/**"  
        );
    // @formatter:on
  }
}
