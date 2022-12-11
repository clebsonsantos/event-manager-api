package com.api.eventmanager.domain.usecases;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.repositories.UserRepository;
import com.api.eventmanager.domain.contracts.usecases.UnsubscribeUserInEvent;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.NotFoundException;

public class UnsubscribeUserInEventImpl implements UnsubscribeUserInEvent {
  final EventRepository eventRepository;
  final UserRepository userRepository;

  public UnsubscribeUserInEventImpl(EventRepository eventRepository, UserRepository userRepository) {
    this.eventRepository = eventRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Event perform(Long userId, Long eventId) throws NotFoundException {
    var user = userRepository.findById(userId);
    if (user == null) {
      throw new NotFoundException("User does not exists");
    }
    var event = eventRepository.findById(eventId);
    if (event == null) {
      throw new NotFoundException("Event does not exists");
    }
    if (event.getUsers().removeIf(users -> users.getId() == user.getId())) {
      return eventRepository.update(event);
    } else {
      throw new NotFoundException("Register does not exists");
    }
  }
}
