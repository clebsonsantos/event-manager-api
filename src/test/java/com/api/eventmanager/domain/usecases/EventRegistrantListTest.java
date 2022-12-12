package com.api.eventmanager.domain.usecases;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.mock;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.usecases.EventRegistrantList;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.domain.errors.NotFoundException;

@TestInstance(Lifecycle.PER_CLASS)
public class EventRegistrantListTest {

  @Autowired
  EventRegistrantList sut;

  Event event;

  @Mock
  EventRepository eventRepository;

  @BeforeAll
  public void mocks() {
    this.event = new Event();
    this.event.setId((long) 1);
    this.event.setName("Java Week Master");
    this.event.setUsers(new ArrayList<User>());
    eventRepository = mock(EventRepository.class);
    when(eventRepository.findById((long) 1)).thenReturn(event);
    this.sut = new EventRegistrantListImpl(eventRepository);
  }

  @Test
  public void shouldBeReturnListRegistrantToEvent() throws NotFoundException {
    var result = this.sut.perform((long) 1);

    Assertions.assertEquals(result, this.event);
    Assertions.assertTrue(result.getUsers().size() == 0);
  }

  @Test
  public void shoulThrowAnExceptionIfUserIsNull() {
  when(eventRepository.findById((long) 1)).thenReturn(null);
  try {
  this.sut.perform((long) 1);
  } catch (NotFoundException e) {
  Assertions.assertEquals(e.getText(), "Event does not exists");
  }
  }
}
