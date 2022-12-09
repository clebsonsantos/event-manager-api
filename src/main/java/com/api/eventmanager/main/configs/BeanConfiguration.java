package com.api.eventmanager.main.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.usecases.CreateNewEventImpl;

@Configuration
public class BeanConfiguration {

  @Bean
  CreateNewEvent createNewEvent(EventRepository eventRepository) {
    return new CreateNewEventImpl(eventRepository);
  }
}