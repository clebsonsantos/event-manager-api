package com.api.eventmanager.domain.contracts.repositories;

import com.api.eventmanager.domain.entities.Event;

public interface EventRepository {
  Event save(Event event);
}
