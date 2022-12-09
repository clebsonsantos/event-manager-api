package com.api.eventmanager.domain.dtos;

import javax.validation.constraints.NotEmpty;

public class UserDTO {

  @NotEmpty
  private String name;

  public UserDTO() {

  }

  public UserDTO(@NotEmpty String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
