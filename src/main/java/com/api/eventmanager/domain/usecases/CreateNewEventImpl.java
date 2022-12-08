package com.api.eventmanager.domain.usecases;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.InvalidDataException;

public class CreateNewEventImpl implements CreateNewEvent {
  final EventRepository eventRepo;

  public CreateNewEventImpl(EventRepository eventRepo) {
    this.eventRepo = eventRepo;
  }

  @Override
  public Event perform(Event event) throws InvalidDataException {
    if (!event.isValid()) {
      throw new InvalidDataException("Invalid data for creating an event");
    }
    return eventRepo.save(event);
  }
}
