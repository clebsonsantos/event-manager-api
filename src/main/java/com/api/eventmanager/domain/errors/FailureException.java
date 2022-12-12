package com.api.eventmanager.domain.errors;

public class FailureException extends Exception {
  private String text;

  public String getText() {
    return this.text;
  }

  public FailureException(String text) {
    super(text);
    this.text = text;
  }
}
