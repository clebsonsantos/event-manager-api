package com.api.eventmanager.infra.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.eventmanager.infra.database.entities.UserEntity;

@Repository
public interface SpringUserRepository extends JpaRepository<UserEntity, Long> {
}