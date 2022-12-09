package com.api.eventmanager.domain.contracts.usecases;

import com.api.eventmanager.domain.dtos.EventDTO;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.InvalidDataException;

public interface CreateNewEvent {
  Event perform(EventDTO event) throws InvalidDataException;
}
