package com.api.eventmanager.domain.usecases;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.usecases.EventRegistrantList;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.NotFoundException;

public class EventRegistrantListImpl implements EventRegistrantList {
  final EventRepository eventRepo;

  public EventRegistrantListImpl(EventRepository eventRepo) {
    this.eventRepo = eventRepo;
  }

  @Override
  public Event perform(Long id) throws NotFoundException {
    var event = eventRepo.findById(id);
    if (event == null) {
      throw new NotFoundException("Event does not exists");
    }
    return event;
  }

}
