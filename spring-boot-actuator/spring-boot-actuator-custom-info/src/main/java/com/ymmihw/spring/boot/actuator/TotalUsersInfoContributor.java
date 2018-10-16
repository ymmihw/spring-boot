package com.ymmihw.spring.boot.actuator;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class TotalUsersInfoContributor implements InfoContributor {

  @Override
  public void contribute(Info.Builder builder) {
    Map<String, Integer> userDetails = new HashMap<>();
    userDetails.put("active", 100);
    userDetails.put("inactive", 200);

    builder.withDetail("users", userDetails);
  }
}
