package com.api.eventmanager.domain.contracts.repositories;

import com.api.eventmanager.domain.entities.User;

public interface UserRepository {
  User save(User user);

  User findById(Long id);
}
