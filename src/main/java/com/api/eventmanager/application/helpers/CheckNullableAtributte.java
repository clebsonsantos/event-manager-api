package com.api.eventmanager.application.helpers;

import java.lang.reflect.Field;

public class CheckNullableAtributte {
  public boolean execute(Field[] fields, Object dto) throws IllegalArgumentException, IllegalAccessException {
    for (Field field : fields) {
      field.setAccessible(true);
      Object objeto = field.get(dto);

      if (objeto == null) {
        return false;
      }
    }
    return true;
  }
}
