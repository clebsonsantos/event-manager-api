package com.api.eventmanager.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
  User sut;

  @BeforeEach
  public void setup() {
    this.sut = new User();

    this.sut.setId((long) 1);
    this.sut.setName("Clebson");

  }

  @Test
  public void shouldBeReturnValidId() {
    Assertions.assertEquals(1, this.sut.getId());
  }

  @Test
  public void shouldBeValidateEntity() {
    Assertions.assertEquals(true, this.sut.isValid());
  }

  @Test
  public void shouldBeReturnedInvalidEntityIfEmptyValues() {
    this.sut.setName(null);
    System.out.println(this.sut.getName());
    Assertions.assertEquals(false, this.sut.isValid());
  }
}
