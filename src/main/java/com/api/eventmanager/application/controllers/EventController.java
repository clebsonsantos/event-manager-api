package com.api.eventmanager.application.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.eventmanager.application.dtos.EventDTO;
import com.api.eventmanager.domain.contracts.usecases.CreateNewEvent;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.errors.InvalidDataException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("events")
public class EventController {
  private final CreateNewEvent createNewEvent;

  public EventController(CreateNewEvent createNewEvent) {
    this.createNewEvent = createNewEvent;
  }

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody @Valid EventDTO eventReceived) {
    try {
      var event = new Event();
      BeanUtils.copyProperties(eventReceived, event);
      var result = createNewEvent.perform(event);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (InvalidDataException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getText());
    }
  }
}
