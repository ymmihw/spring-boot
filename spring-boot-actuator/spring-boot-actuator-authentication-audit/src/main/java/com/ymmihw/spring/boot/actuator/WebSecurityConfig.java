package com.ymmihw.spring.boot.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().logout().disable()
        .csrf().disable();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemoryAuthentication =
        auth.inMemoryAuthentication();
    inMemoryAuthentication.withUser("jim").password(encoder.encode("jim")).roles("USER",
        "ACTUATOR");
    inMemoryAuthentication.withUser("pam").password(encoder.encode("pam")).roles("USER");
    inMemoryAuthentication.withUser("michael").password(encoder.encode("michael")).roles("MANAGER");
  }
}
