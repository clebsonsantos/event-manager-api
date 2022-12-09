package com.api.eventmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.api.eventmanager.infra.database.repositories.SpringEventRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = SpringEventRepository.class)
public class EventManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
	}

}
