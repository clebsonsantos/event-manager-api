package com.api.eventmanager.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.eventmanager.application.helpers.CheckNullableAtributte;
import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.dtos.EventDTO;
import com.api.eventmanager.domain.errors.InvalidDataException;
import java.lang.reflect.Field;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("events")
public class EventController {
  private final CreateNewEvent createNewEvent;

  public EventController(CreateNewEvent createNewEvent) {
    this.createNewEvent = createNewEvent;
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
}
