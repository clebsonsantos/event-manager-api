package com.api.eventmanager.main.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.repositories.UserRepository;
import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.contracts.usecases.CreateNewUser;
import com.api.eventmanager.domain.contracts.usecases.SubscribeUserInEvent;
import com.api.eventmanager.domain.contracts.usecases.UnsubscribeUserInEvent;
import com.api.eventmanager.domain.usecases.CreateNewEventImpl;
import com.api.eventmanager.domain.usecases.CreateNewUserImpl;
import com.api.eventmanager.domain.usecases.SubscribeUserInEventImpl;
import com.api.eventmanager.domain.usecases.UnsubscribeUserInEventImpl;

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

  @Bean
  SubscribeUserInEvent subscribeUserInEvent(EventRepository eventRepository, UserRepository userRepository) {
    return new SubscribeUserInEventImpl(eventRepository, userRepository);
  }

  @Bean
  UnsubscribeUserInEvent unsubscribeUserInEvent(EventRepository eventRepository, UserRepository userRepository) {
    return new UnsubscribeUserInEventImpl(eventRepository, userRepository);
  }
}