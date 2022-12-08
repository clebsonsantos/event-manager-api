package com.api.eventmanager.domain.entities;

import java.util.Date;

public class Event {
  private int id;
  private String name;
  private int vacancies;
  private Date startDate;
  private Date endDate;

  public Event() {
  }

  public Event(int id, String name, int vacancies, Date startDate, Date endDate) {
    this.id = id;
    this.name = name;
    this.vacancies = vacancies;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public boolean isValid() {
    if (this.id < 0
        || this.name.length() < 0
        || this.vacancies < 0
        || this.startDate.getTime() < 0
        || this.endDate.getTime() < 0) {
      return false;
    }
    return true;
  }

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
