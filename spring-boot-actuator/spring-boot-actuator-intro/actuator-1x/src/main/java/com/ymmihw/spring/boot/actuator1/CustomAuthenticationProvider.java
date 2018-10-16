package com.ymmihw.spring.boot.actuator1;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
  @Autowired
  private CounterService counterService;

  @Override
  public Authentication authenticate(final Authentication authentication)
      throws AuthenticationException {
    final String name = authentication.getName();
    final String password = authentication.getCredentials().toString();
    if (name.equals("1") && password.equals("1")) {
      counterService.increment("counter.login.success");
      final List<GrantedAuthority> grantedAuths = new ArrayList<>();
      grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
      grantedAuths.add(new SimpleGrantedAuthority("ROLE_ACTUATOR"));

      final UserDetails principal = new User(name, password, grantedAuths);
      final Authentication auth =
          new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
      return auth;
    } else {
      counterService.increment("counter.login.failure");
      return null;
    }
  }

  @Override
  public boolean supports(final Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
