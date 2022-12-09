package com.api.eventmanager.domain.dtos;

import java.util.Date;

public class EventDTO {
  private String name;
  private Integer vacancies;
  private Date startDate;
  private Date endDate;

  public EventDTO() {

  }

  public EventDTO(String name, Integer vacancies, Date startDate, Date endDate) {
    this.name = name;
    this.vacancies = vacancies;
    this.startDate = startDate;
    this.endDate = endDate;
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
