package com.api.eventmanager.domain.usecases;

import com.api.eventmanager.domain.contracts.repositories.UserRepository;
import com.api.eventmanager.domain.contracts.usecases.ListUsersSubscription;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.NotFoundException;

public class ListUsersSubscriptionImpl implements ListUsersSubscription {
  final UserRepository userRepository;

  public ListUsersSubscriptionImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User perform(Long id) throws NotFoundException {
    var user = userRepository.findUsersEventsById(id);
    if (user == null) {
      throw new NotFoundException("User not found");
    }
    return user;
  }
}
