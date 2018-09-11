package com.ymmihw.spring.boot.admin.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;

@Configuration
public class HazelcastConfig {
  @Bean
  public Config hazelcast() {
    MapConfig mapConfig =
        new MapConfig("spring-boot-admin-event-store").setInMemoryFormat(InMemoryFormat.OBJECT)
            .setBackupCount(1).setEvictionPolicy(EvictionPolicy.NONE);
    return new Config().setProperty("hazelcast.jmx", "true").addMapConfig(mapConfig);
  }
}
