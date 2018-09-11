package com.ymmihw.spring.boot.admin.configs;

import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.notify.LoggingNotifier;
import de.codecentric.boot.admin.server.notify.RemindingNotifier;
import de.codecentric.boot.admin.server.notify.filter.FilteringNotifier;

@Configuration
public class NotifierConfig {
  private final InstanceRepository repository;

  public NotifierConfig(InstanceRepository repository) {
    this.repository = repository;
  }

  @Bean
  public LoggingNotifier notifier() {
    return new LoggingNotifier(repository);
  }

  @Bean
  public FilteringNotifier filteringNotifier() {
    return new FilteringNotifier(notifier(), repository);
  }

  @Bean
  @Primary
  public RemindingNotifier remindingNotifier() {
    RemindingNotifier remindingNotifier = new RemindingNotifier(filteringNotifier(), repository);
    remindingNotifier.setReminderPeriod(Duration.ofMillis(5));
    remindingNotifier.setCheckReminderInverval(Duration.ofSeconds(10));
    return remindingNotifier;
  }
}
