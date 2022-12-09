package com.api.eventmanager.domain.contracts.usecases;

import com.api.eventmanager.domain.dtos.UserDTO;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.InvalidDataException;

public interface CreateNewUser {
  User perform(UserDTO user) throws InvalidDataException;
}
