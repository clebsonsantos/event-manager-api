package com.api.eventmanager.infra.database.repositories;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.api.eventmanager.domain.contracts.repositories.UserRepository;
import com.api.eventmanager.domain.entities.Event;
import com.api.eventmanager.domain.entities.User;
import com.api.eventmanager.infra.database.entities.UserEntity;

@Component
public class UserRepositoryImpl implements UserRepository {
  final SpringUserRepository springUserRepository;

  public UserRepositoryImpl(SpringUserRepository springUserRepository) {
    this.springUserRepository = springUserRepository;
  }

  @Override
  public User save(User user) {
    var userEntity = new UserEntity();
    BeanUtils.copyProperties(user, userEntity);
    var result = this.springUserRepository.save(userEntity);
    return new User(
        result.getId(),
        result.getName());
  }

  @Override
  public User findById(Long id) {
    var result = this.springUserRepository.findById(id);
    if (!result.isPresent()) {
      return null;
    }
    var user = result.get();
    return new User(
        user.getId(),
        user.getName());
  }

  public User findUsersEventsById(Long id) {
    var result = this.springUserRepository.findById(id);
    if (!result.isPresent()) {
      return null;
    }
    var list = new ArrayList<Event>();
    var user = result.get();
    for (var event : user.getEvents()) {
      var eventResult = new Event();
      BeanUtils.copyProperties(event, eventResult);
      list.add(eventResult);
    }
    return new User(
        user.getId(),
        user.getName(),
        list);
  }

}
