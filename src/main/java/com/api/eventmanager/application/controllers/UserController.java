package com.api.eventmanager.application.controllers;

import java.lang.reflect.Field;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.eventmanager.application.helpers.CheckNullableAtributte;
import com.api.eventmanager.domain.contracts.usecases.CreateNewUser;
import com.api.eventmanager.domain.dtos.UserDTO;
import com.api.eventmanager.domain.errors.InvalidDataException;

public class UserController {
  final CreateNewUser createNewUser;

  public UserController(CreateNewUser createNewUser) {
    this.createNewUser = createNewUser;
  }

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody UserDTO userReceived)
      throws IllegalArgumentException, IllegalAccessException {
    try {
      Class<UserDTO> eventClass = UserDTO.class;
      Field[] fields = eventClass.getDeclaredFields();
      CheckNullableAtributte validFields = new CheckNullableAtributte();

      if (!validFields.execute(fields, userReceived)) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new Error("Check that all the properties are correct").getMessage());
      }
      var result = createNewUser.perform(userReceived);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } catch (

    InvalidDataException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getText());
    }
  }
}
