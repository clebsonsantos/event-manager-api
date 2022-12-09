package com.api.eventmanager.domain.entities;

public class User {
  private Long id;
  private String name;

  public User() {
  }

  public User(Long id, String name) {
    this.id = id;
    this.name = name;
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

}
