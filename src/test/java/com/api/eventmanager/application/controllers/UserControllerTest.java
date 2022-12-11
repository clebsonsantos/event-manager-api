package com.api.eventmanager.application.controllers;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.api.eventmanager.domain.contracts.usecases.CreateNewUser;
import com.api.eventmanager.domain.contracts.usecases.ListUsersSubscription;
import com.api.eventmanager.domain.dtos.UserDTO;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.InvalidDataException;

@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {
  @InjectMocks
  UserController sut;

  @Mock
  CreateNewUser createNewUser;

  @Mock
  ListUsersSubscription listUsersSubscription;

  @BeforeAll
  public void setup() throws InvalidDataException {
    MockitoAnnotations.openMocks(this);
    this.sut = new UserController(createNewUser, listUsersSubscription);
  }

  @Test
  public void shouldBeReturnCreationStatus() throws InvalidDataException, IllegalArgumentException, IllegalAccessException {
    when(createNewUser.perform(Mockito.any(UserDTO.class))).thenReturn(Mockito.any(User.class));
    var user = new UserDTO();
    user.setName("Clebson");
    var result = this.sut.save(user);

    Assertions.assertEquals(201, result.getStatusCodeValue());
  }

  @Test
  public void shouldBeReturnBadRequestIfInvalid()
      throws InvalidDataException, IllegalArgumentException, IllegalAccessException {
    doThrow(new InvalidDataException("any-error")).when(createNewUser).perform(Mockito.any(UserDTO.class));
    var user = new UserDTO();
    user.setName(null);
    var result = this.sut.save(user);

    Assertions.assertEquals(400, result.getStatusCodeValue());
    verify(createNewUser, times(0)).perform(user);
  }
}
