package com.api.eventmanager.domain.entities;

import java.util.List;

public class User {
  private Long id;
  private String name;
  private List<Event> events;

  public User() {

  }

  public User(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public User(Long id, String name, List<Event> events) {
    this.id = id;
    this.name = name;
    this.events = events;
  }

  public boolean isValid() {
    if (this.name == null || this.name.length() == 0) {
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

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

}
