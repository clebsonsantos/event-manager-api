package com.api.eventmanager.domain.entities;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {
  Event sut;

  @BeforeEach
  public void setup() {
    this.sut = new Event();

    this.sut.setId(1);
    this.sut.setName("Next Level Week");
    this.sut.setVacancies(2);
    this.sut.setStartDate(new Date());
    this.sut.setEndDate(new Date());
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
    this.sut.setStartDate(null);
    System.out.println(this.sut.getName());
    Assertions.assertEquals(false, this.sut.isValid());
  }
}
