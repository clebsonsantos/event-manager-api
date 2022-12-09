package com.api.eventmanager.domain.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {
  Event sut;

  @BeforeEach
  public void setup() {
    this.sut = new Event();

    this.sut.setId((long) 1);
    this.sut.setName("Next Level Week");
    this.sut.setVacancies(2);
    this.sut.setStartDate(LocalDateTime.now(ZoneId.of("UTC")));
    this.sut.setEndDate(LocalDateTime.now(ZoneId.of("UTC")));
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
