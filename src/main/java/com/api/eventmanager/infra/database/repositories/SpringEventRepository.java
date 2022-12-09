package com.api.eventmanager.infra.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.eventmanager.infra.database.entities.EventEntity;

@Repository
public interface SpringEventRepository extends JpaRepository<EventEntity, Long> {
}