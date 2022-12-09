package com.api.eventmanager.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
  private Long id;
  private String name;
  private Integer vacancies;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private List<User> users;

  public Event() {
  }

  public Event(Long id, String name, int vacancies, LocalDateTime startDate, LocalDateTime endDate) {
    this.id = id;
    this.name = name;
    this.vacancies = vacancies;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public boolean isValid() {
    if (this.name == null || this.name.length() == 0
        || this.vacancies <= 0
        || this.startDate == null
        || this.endDate == null) {
      return false;
    }
    return true;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

}
