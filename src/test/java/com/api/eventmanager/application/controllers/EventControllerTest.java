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

import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.contracts.usecases.SubscribeUserInEvent;
import com.api.eventmanager.domain.contracts.usecases.UnsubscribeUserInEvent;
import com.api.eventmanager.domain.dtos.EventDTO;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.InvalidDataException;

@TestInstance(Lifecycle.PER_CLASS)
public class EventControllerTest {
  @InjectMocks
  EventController sut;

  @Mock
  CreateNewEvent createEvent;

  @Mock
  SubscribeUserInEvent subscribeUserInEvent;

  @Mock
  UnsubscribeUserInEvent unsubscribeUserInEvent;

  @BeforeAll
  public void setup() throws InvalidDataException {
    MockitoAnnotations.openMocks(this);
    this.sut = new EventController(createEvent, subscribeUserInEvent, unsubscribeUserInEvent);
  }

  @Test
  public void shouldReceivedEventViaRequestAndCreatedItAndReturnCreationStatus() throws InvalidDataException, IllegalArgumentException, IllegalAccessException {
    when(createEvent.perform(Mockito.any(EventDTO.class))).thenReturn(Mockito.any(Event.class));
    var event = new EventDTO();
    event.setName("Java Week");
    event.setVacancies(200);
    event.setStartDate(new Date());
    event.setEndDate(new Date());
    var result = this.sut.save(event);

    Assertions.assertEquals(201, result.getStatusCodeValue());
  }

  @Test
  public void shouldReceivedEventAndReturnBadRequestIfInvalid()
      throws InvalidDataException, IllegalArgumentException, IllegalAccessException {
    doThrow(new InvalidDataException("any-error")).when(createEvent).perform(Mockito.any(EventDTO.class));
    var eventdto = new EventDTO();
    eventdto.setName(null);
    eventdto.setVacancies(0);

    var result = this.sut.save(eventdto);

    Assertions.assertEquals(400, result.getStatusCodeValue());
    verify(createEvent, times(0)).perform(Mockito.any(EventDTO.class));
  }
}
