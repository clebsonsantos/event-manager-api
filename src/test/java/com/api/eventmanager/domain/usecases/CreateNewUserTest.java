package com.api.eventmanager.domain.usecases;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.eventmanager.domain.contracts.repositories.UserRepository;
import com.api.eventmanager.domain.contracts.usecases.CreateNewUser;
import com.api.eventmanager.domain.dtos.UserDTO;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.InvalidDataException;

@TestInstance(Lifecycle.PER_CLASS)
public class CreateNewUserTest {

  @Autowired
  CreateNewUser sut;

  UserDTO userDTO;
  User event;

  @Mock
  UserRepository userRepository;

  @BeforeAll
  public void mocks() {
    this.userDTO = new UserDTO("Clebson");
    userRepository = mock(UserRepository.class);
    when(userRepository.save(event)).thenReturn(event);
    this.sut = new CreateNewUserImpl(userRepository);
  }

  @Test
  public void shouldBeCreateAndReturnValidUser() {
    try {
      var result = this.sut.perform(this.userDTO);

      Assertions.assertEquals(result, userDTO);
    } catch (InvalidDataException e) {

    }
  }

  @Test
  public void shoulThrowAnExceptionIfUserIsInvalid() {
    try {
      this.userDTO.setName(null);

      this.sut.perform(this.userDTO);
    } catch (InvalidDataException e) {
      Assertions.assertEquals(e.getText(), "Invalid data for creating an user");
    }
  }
}
