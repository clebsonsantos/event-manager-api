package com.api.eventmanager.domain.usecases;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.repositories.UserRepository;
import com.api.eventmanager.domain.contracts.usecases.SubscribeUserInEvent;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.NotFoundException;

@TestInstance(Lifecycle.PER_CLASS)
public class SubscribeUserInEventTest {

  @Autowired
  SubscribeUserInEvent sut;

  User user;
  Event event;

  @Mock
  UserRepository userRepository;

  @Mock
  EventRepository eventRepository;

  @BeforeAll
  public void mocks() {
    this.user = new User();
    this.user.setId((long) 1);
    this.user.setName("Clebson");

    this.event = new Event();
    this.event.setId((long) 1);
    this.event.setName("Java Week");
    this.event.setStartDate(LocalDateTime.now(ZoneId.of("UTC")));
    this.event.setEndDate(LocalDateTime.now(ZoneId.of("UTC")));
    this.event.setVacancies(20);
    var list = new ArrayList<User>();
    list.add(user);
    this.event.setUsers(list);

    userRepository = mock(UserRepository.class);
    eventRepository = mock(EventRepository.class);
    when(userRepository.findById((long) 1)).thenReturn(user);
    when(eventRepository.findById((long) 1)).thenReturn(event);
    this.sut = new SubscribeUserInEventImpl(eventRepository, userRepository);
  }

  @Test
  public void shouldBeReturnValidEvent() {
    try {
      var result = this.sut.perform((long) 1, (long) 1);

      Assertions.assertEquals(result, this.event);
    } catch (NotFoundException e) {

    }
  }

  @Test
  public void shoulThrowAnExceptionIfEventIsNull() {
    when(eventRepository.findById((long) 1)).thenReturn(null);
    try {
      this.sut.perform((long) 1, (long) 1);
    } catch (NotFoundException e) {
      Assertions.assertEquals(e.getText(), "Event does not exists");
    }
  }
}
