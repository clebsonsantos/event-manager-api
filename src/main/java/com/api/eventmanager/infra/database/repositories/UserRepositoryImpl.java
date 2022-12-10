package com.api.eventmanager.infra.database.repositories;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.api.eventmanager.domain.contracts.repositories.UserRepository;
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
    var result = this.springUserRepository.findById(id).get();
    return new User(
        result.getId(),
        result.getName());
  }

}
