package com.api.eventmanager.application.controllers;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.api.eventmanager.application.dtos.EventDTO;
import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.InvalidDataException;

@TestInstance(Lifecycle.PER_CLASS)
public class EventControllerTest {
  @InjectMocks
  EventController sut;

  @Mock
  CreateNewEvent createEvent;

  @BeforeAll
  public void setup() throws InvalidDataException {
    MockitoAnnotations.openMocks(this);
    this.sut = new EventController(createEvent);
  }

  @Test
  public void shouldReceivedEventViaRequestAndCreatedItAndReturnCreationStatus() throws InvalidDataException {
    when(createEvent.perform(Mockito.any(Event.class))).thenReturn(Mockito.any(Event.class));
    var event = new EventDTO();
    event.setId(2);
    event.setName("Java Week");
    event.setVacancies(200);
    event.setStartDate(new Date());
    event.setEndDate(new Date());
    var result = this.sut.save(event);

    Assertions.assertEquals(201, result.getStatusCodeValue());
  }

  @Test
  public void shouldReceivedEventAndReturnBadRequestIfInvalid() throws InvalidDataException {
    doThrow(new InvalidDataException("any-error")).when(createEvent).perform(Mockito.any(Event.class));
    var eventdto = new EventDTO();
    eventdto.setId(0);

    var result = this.sut.save(eventdto);

    Assertions.assertEquals(400, result.getStatusCodeValue());
    verify(createEvent, times(1)).perform(Mockito.any(Event.class));
  }
}
