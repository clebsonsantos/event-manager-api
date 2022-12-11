package com.api.eventmanager.domain.usecases;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.eventmanager.domain.contracts.repositories.UserRepository;
import com.api.eventmanager.domain.contracts.usecases.ListUsersSubscription;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.NotFoundException;

@TestInstance(Lifecycle.PER_CLASS)
public class ListUsersSubscriptionTest {

  @Autowired
  ListUsersSubscription sut;

  User user;

  @Mock
  UserRepository userRepository;

  @BeforeAll
  public void mocks() {
    this.user = new User();
    this.user.setId((long) 1);
    this.user.setName("Clebson");
    this.user.setEvents(new ArrayList<Event>());

    userRepository = mock(UserRepository.class);
    when(userRepository.findUsersEventsById((long) 1)).thenReturn(user);
    this.sut = new ListUsersSubscriptionImpl(userRepository);
  }

  @Test
  public void shouldBeReturnValidUser() {
    try {
      var result = this.sut.perform((long) 1);

      Assertions.assertEquals(result, this.user);
      Assertions.assertTrue(result.getEvents().size() == 0);
    } catch (NotFoundException e) {

    }
  }

  @Test
  public void shoulThrowAnExceptionIfUserIsNull() {
    when(userRepository.findUsersEventsById((long) 1)).thenReturn(null);
    try {
      this.sut.perform((long) 1);
    } catch (NotFoundException e) {
      Assertions.assertEquals(e.getText(), "User not found");
    }
  }
}
