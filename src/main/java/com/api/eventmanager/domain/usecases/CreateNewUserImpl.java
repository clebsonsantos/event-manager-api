package com.api.eventmanager.domain.usecases;

import com.api.eventmanager.domain.contracts.repositories.UserRepository;
import com.api.eventmanager.domain.contracts.usecases.CreateNewUser;
import com.api.eventmanager.domain.dtos.UserDTO;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.InvalidDataException;

public class CreateNewUserImpl implements CreateNewUser {
  final UserRepository userRepository;

  public CreateNewUserImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User perform(UserDTO user) throws InvalidDataException {
    var userEntity = new User();
    userEntity.setName(user.getName());
    if (!userEntity.isValid()) {
      throw new InvalidDataException("Invalid data for creating an user");
    }
    return userRepository.save(userEntity);
  }

}
