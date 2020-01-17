package com.rafalazar.springboot.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.springboot.bootcamp.app.documents.Personal;

import reactor.core.publisher.Mono;

public interface PersonalRepository extends ReactiveMongoRepository<Personal, String>{
	
	public Mono<Personal> findByLastName(String lastName);
	public Mono<Personal> findByDni(String dni);

}
