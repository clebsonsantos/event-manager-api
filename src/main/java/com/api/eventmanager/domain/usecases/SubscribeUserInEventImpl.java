package com.api.eventmanager.domain.usecases;

import java.util.ArrayList;
import java.util.List;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.repositories.UserRepository;
import com.api.eventmanager.domain.contracts.usecases.SubscribeUserInEvent;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.FailureException;
import com.api.eventmanager.domain.errors.NotFoundException;

public class SubscribeUserInEventImpl implements SubscribeUserInEvent {
  final EventRepository eventRepository;
  final UserRepository userRepository;

  public SubscribeUserInEventImpl(EventRepository eventRepository, UserRepository userRepository) {
    this.eventRepository = eventRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Event perform(Long userId, Long eventId) throws NotFoundException, FailureException {
    var user = userRepository.findById(userId);
    if (user == null) {
      throw new NotFoundException("User does not exists");
    }
    var event = eventRepository.findById(eventId);
    if (event == null) {
      throw new NotFoundException("Event does not exists");
    }
    for (var userEvent : event.getUsers()) {
      if (userEvent.getId() == user.getId()) {
        throw new NotFoundException("Already registered user");
      }
    }
    if (event.getUsers() == null || event.getUsers().size() == 0) {
      List<User> list = new ArrayList<>();
      list.add(user);
      event.setUsers(list);
    } else {
      if (event.getUsers().size() == event.getVacancies()) {
        throw new FailureException("No more vacancies available");
      }
      event.getUsers().add(user);
      event.setUsers(event.getUsers());
    }
    return eventRepository.update(event);
  }
}
