package com.api.eventmanager.main.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.repositories.UserRepository;
import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.contracts.usecases.CreateNewUser;
import com.api.eventmanager.domain.usecases.CreateNewEventImpl;
import com.api.eventmanager.domain.usecases.CreateNewUserImpl;

@Configuration
public class BeanConfigurationFactories {

  @Bean
  CreateNewEvent createNewEvent(EventRepository eventRepository) {
    return new CreateNewEventImpl(eventRepository);
  }

  @Bean
  CreateNewUser createNewUser(UserRepository userRepository) {
    return new CreateNewUserImpl(userRepository);
  }
}