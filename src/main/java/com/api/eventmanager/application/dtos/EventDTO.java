package com.api.eventmanager.application.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class EventDTO {
  @NotBlank
  private int id;

  @NotBlank
  private String name;

  @NotBlank
  private int vacancies;

  @NotBlank
  private Date startDate;

  @NotBlank
  private Date endDate;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getVacancies() {
    return vacancies;
  }

  public void setVacancies(int vacancies) {
    this.vacancies = vacancies;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

}
