package com.api.eventmanager.domain.usecases;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.api.eventmanager.application.dtos.EventDTO;
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
  public Event perform(EventDTO event) throws InvalidDataException {
    if (event.getStartDate() == null || event.getEndDate() == null) {
      throw new InvalidDataException("Invalid data for creating an event");
    }
    var eventEntity = new Event();
    eventEntity.setName(event.getName());
    eventEntity.setVacancies(event.getVacancies());
    eventEntity.setStartDate(this.convertDateTime(event.getStartDate()));
    eventEntity.setEndDate(this.convertDateTime(event.getEndDate()));

    if (!eventEntity.isValid()) {
      throw new InvalidDataException("Invalid data for creating an event");
    }
    return eventRepo.save(eventEntity);
  }

  private LocalDateTime convertDateTime(Date date) {
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));
  }
}
