package com.api.eventmanager.infra.database.repositories;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.api.eventmanager.domain.contracts.repositories.EventRepository;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.infra.database.entities.EventEntity;
import com.api.eventmanager.infra.database.entities.UserEntity;

@Component
public class EventRepositoryImpl implements EventRepository {
  private final SpringEventRepository springEventRepository;

  public EventRepositoryImpl(SpringEventRepository springEventRepository) {
    this.springEventRepository = springEventRepository;
  }

  @Override
  public Event save(Event event) {
    var eventEntity = new EventEntity();
    BeanUtils.copyProperties(event, eventEntity);
    var result = this.springEventRepository.save(eventEntity);

    var listUserEntity = result.getUsers();

    var listsUser = new ArrayList<User>();
    for (var user : listUserEntity) {
      var newUser = new User(user.getId(), user.getName());
      listsUser.add(newUser);
    }
    return new Event(
        result.getId(),
        result.getName(),
        result.getVacancies(),
        result.getStartDate(),
        result.getEndDate());
  }

  @Override
  public Event update(Event event) {
    var eventEntity = new EventEntity();
    BeanUtils.copyProperties(event, eventEntity);

    var list = new ArrayList<UserEntity>();
    for (var user : event.getUsers()) {
      var entity = new UserEntity();
      entity.setId(user.getId());
      entity.setName(user.getName());
      list.add(entity);
    }
    eventEntity.setUsers(list);

    var result = this.springEventRepository.save(eventEntity);
    var resultEvent = new Event();

    var listResult = new ArrayList<User>();
    for (var user : result.getUsers()) {
      var entity = new User();
      entity.setId(user.getId());
      entity.setName(user.getName());
      listResult.add(entity);
    }
    resultEvent.setUsers(listResult);
    BeanUtils.copyProperties(result, resultEvent);
    return resultEvent;
  }

  @Override
  public Event findById(Long id) {
    var result = this.springEventRepository.findById(id);
    if (!result.isPresent()) {
      return null;
    }
    var event = result.get();
    var listUserEntity = event.getUsers();

    var listsUser = new ArrayList<User>();
    for (var user : listUserEntity) {
      var newUser = new User(user.getId(), user.getName());
      listsUser.add(newUser);
    }
    return new Event(
        event.getId(),
        event.getName(),
        event.getVacancies(),
        event.getStartDate(),
        event.getEndDate(),
        listsUser);
  }
}
