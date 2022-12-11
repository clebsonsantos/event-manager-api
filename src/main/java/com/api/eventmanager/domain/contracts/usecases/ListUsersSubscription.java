package com.api.eventmanager.domain.contracts.usecases;

import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.NotFoundException;

public interface ListUsersSubscription {
  User perform(Long id) throws NotFoundException;
}
