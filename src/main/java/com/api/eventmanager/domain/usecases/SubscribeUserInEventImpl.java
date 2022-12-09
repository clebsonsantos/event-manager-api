package com.api.eventmanager.domain.usecases;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.usecases.SubscribeUserInEvent;
import com.api.eventmanager.domain.dtos.UserDTO;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.InvalidDataException;
import com.api.eventmanager.domain.errors.NotFoundException;

public class SubscribeUserInEventImpl implements SubscribeUserInEvent {
  final EventRepository eventRepository;

  public SubscribeUserInEventImpl(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public Event perform(UserDTO user, Long eventId) throws InvalidDataException, NotFoundException {
    var userEntity = new User();
    userEntity.setName(user.getName());
    if (!userEntity.isValid()) {
      throw new InvalidDataException("Unable to enroll user, date is invalid");
    }

    var event = eventRepository.findById(eventId);
    if (event == null) {
      throw new NotFoundException("Event does not exists");
    }

    event.getUsers().add(userEntity);
    event.setUsers(event.getUsers());
    return eventRepository.update(event);
  }
}
