package com.api.eventmanager.infra.database.repositories;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.infra.database.entities.EventEntity;

@Component
public class EventRepositoryImpl implements EventRepository {
  private final SpringEventRepository springEventRepository;

  public EventRepositoryImpl(SpringEventRepository springEventRepository) {
    this.springEventRepository = springEventRepository;
  }

  @Override
  public Event save(Event event) {
    var eventEntity = new EventEntity();
    BeanUtils.copyProperties(event, eventEntity);
    var result = this.springEventRepository.save(eventEntity);
    return new Event(
        result.getId(),
        result.getName(),
        result.getVacancies(),
        result.getStartDate(),
        result.getEndDate());
  }
}
