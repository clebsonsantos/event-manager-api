package com.api.eventmanager.application.controllers;

import java.lang.reflect.Field;

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
import com.api.eventmanager.domain.contracts.usecases.CreateNewUser;
import com.api.eventmanager.domain.contracts.usecases.ListUsersSubscription;
import com.api.eventmanager.domain.dtos.UserDTO;
import com.api.eventmanager.domain.errors.InvalidDataException;
import com.api.eventmanager.domain.errors.NotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("users")
public class UserController {
  final CreateNewUser createNewUser;
  final ListUsersSubscription listUsersSubscription;

  public UserController(CreateNewUser createNewUser, ListUsersSubscription listUsersSubscription) {
    this.createNewUser = createNewUser;
    this.listUsersSubscription = listUsersSubscription;
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

  @GetMapping("/{id}")
  public ResponseEntity<Object> listUserSubscription(@PathVariable(value = "id") Long id) {
    try {
      var result = listUsersSubscription.perform(id);
      return ResponseEntity.status(HttpStatus.OK).body(result);
    } catch (NotFoundException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getText());
    }
  }
}
