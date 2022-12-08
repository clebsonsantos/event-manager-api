package com.api.eventmanager.application.controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;

import com.api.eventmanager.application.dtos.EventDTO;
import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.InvalidDataException;

@TestInstance(Lifecycle.PER_CLASS)
public class EventControllerTest {
  EventController sut;

  @Mock
  CreateNewEvent createNewEvent;

  @BeforeAll
  public void setup() throws InvalidDataException {
    var event = new Event(1, "Any event", 1, new Date(), new Date());
    CreateNewEvent createEvent = mock(CreateNewEvent.class);
    when(createEvent.perform(event)).thenReturn(event);
    this.sut = new EventController(createEvent);
  }

  @Test
  public void shouldReceivedEventViaRequestAndCreatedItAndReturnCreationStatus() {
    var event = new EventDTO();
    event.setId(2);
    event.setName("Java Week");
    event.setVacancies(200);
    event.setStartDate(new Date());
    event.setEndDate(new Date());
    var result = this.sut.save(event);

    Assertions.assertEquals(result.getStatusCodeValue(), 201);
  }
}
