package com.rafalazar.springboot.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.springboot.bootcamp.app.documents.Personal;

public interface PersonalRepository extends ReactiveMongoRepository<Personal, String>{

}
