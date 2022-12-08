package com.api.eventmanager.domain.errors;

public class InvalidDataException extends Exception {
  private String text;

  public String getText() {
    return this.text;
  }

  public InvalidDataException(String text) {
    super(text);
    this.text = text;
  }
}
