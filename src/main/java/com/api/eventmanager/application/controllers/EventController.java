package com.api.eventmanager.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.eventmanager.application.helpers.CheckNullableAtributte;
import com.api.eventmanager.application.helpers.RequestSubscribe;
import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.contracts.usecases.EventRegistrantList;
import com.api.eventmanager.domain.contracts.usecases.SubscribeUserInEvent;
import com.api.eventmanager.domain.contracts.usecases.UnsubscribeUserInEvent;
import com.api.eventmanager.domain.dtos.EventDTO;
import com.api.eventmanager.domain.errors.InvalidDataException;
import com.api.eventmanager.domain.errors.NotFoundException;

import java.lang.reflect.Field;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("events")
public class EventController {
  private final CreateNewEvent createNewEvent;
  private final SubscribeUserInEvent subscribeUserInEvent;
  private final UnsubscribeUserInEvent unsubscribeUserInEvent;
  private final EventRegistrantList eventRegistrantList;

  public EventController(CreateNewEvent createNewEvent, SubscribeUserInEvent subscribeUserInEvent,
      UnsubscribeUserInEvent unsubscribeUserInEvent, EventRegistrantList eventRegistrantList) {
    this.createNewEvent = createNewEvent;
    this.subscribeUserInEvent = subscribeUserInEvent;
    this.unsubscribeUserInEvent = unsubscribeUserInEvent;
    this.eventRegistrantList = eventRegistrantList;
  }

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody EventDTO eventReceived)
      throws IllegalArgumentException, IllegalAccessException {
    try {
      Class<EventDTO> eventClass = EventDTO.class;
      Field[] fields = eventClass.getDeclaredFields();
      CheckNullableAtributte validFields = new CheckNullableAtributte();

      if (!validFields.execute(fields, eventReceived)) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new Error("Check that all the properties are correct").getMessage());
      }
      var result = createNewEvent.perform(eventReceived);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (InvalidDataException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getText());
    }
  }

  @PostMapping("/subscribe")
  public ResponseEntity<Object> subscribe(@RequestBody RequestSubscribe body) {
    try {
      var result = subscribeUserInEvent.perform(body.getUserId(), body.getEventId());
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getText());
    }
  }

  @PostMapping("/unsubscribe")
  public ResponseEntity<Object> unsubscribe(@RequestBody RequestSubscribe body) {
    try {
      var result = unsubscribeUserInEvent.perform(body.getUserId(), body.getEventId());
      return ResponseEntity.status(HttpStatus.OK).body(result);
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getText());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> eventRegistrantList(@PathVariable(value = "id") Long id) {
    try {
      var result = eventRegistrantList.perform(id);
      return ResponseEntity.status(HttpStatus.OK).body(result);
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getText());
    }
  }

}
