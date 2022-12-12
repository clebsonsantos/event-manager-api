package com.api.eventmanager.domain.contracts.usecases;

import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.FailureException;
import com.api.eventmanager.domain.errors.NotFoundException;

public interface SubscribeUserInEvent {
  Event perform(Long userId, Long eventId) throws NotFoundException, FailureException;
}
