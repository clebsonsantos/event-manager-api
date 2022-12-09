package com.api.eventmanager.domain.contracts.usecases;

import com.api.eventmanager.domain.dtos.UserDTO;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.InvalidDataException;
import com.api.eventmanager.domain.errors.NotFoundException;

public interface SubscribeUserInEvent {
  Event perform(UserDTO user, Long eventId) throws InvalidDataException, NotFoundException;
}
