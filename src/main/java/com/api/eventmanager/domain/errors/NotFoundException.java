package com.api.eventmanager.domain.errors;

public class NotFoundException extends Exception {
  private String text;

  public String getText() {
    return this.text;
  }

  public NotFoundException(String text) {
    super(text);
    this.text = text;
  }
}
