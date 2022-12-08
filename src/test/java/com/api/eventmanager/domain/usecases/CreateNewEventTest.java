package com.api.eventmanager.domain.usecases;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.InvalidDataException;

@TestInstance(Lifecycle.PER_CLASS)
public class CreateNewEventTest {

  @Autowired
  CreateNewEvent sut;

  Event event;

  @Mock
  EventRepository eventRepository;

  @BeforeAll
  public void mocks() {
    this.event = new Event(1, "Any event", 1, new Date(), new Date());
    EventRepository eventRepository = mock(EventRepository.class);
    when(eventRepository.save(event)).thenReturn(event);
    this.sut = new CreateNewEventImpl(eventRepository);
  }

  @Test
  public void shouldBeCreateAndReturnValidEvent() {
    try {
      var result = this.sut.perform(this.event);

      Assertions.assertEquals(result, event);
      Assertions.assertTrue(event.isValid());
    } catch (InvalidDataException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void shoulThrowAnExceptionIfEventIsInvalid() {
    try {
      this.event.setName(null);
      this.event.setVacancies(0);

      this.sut.perform(this.event);
    } catch (InvalidDataException e) {
      Assertions.assertEquals(e.getText(), "Invalid data for creating an event");
    }
  }
}
