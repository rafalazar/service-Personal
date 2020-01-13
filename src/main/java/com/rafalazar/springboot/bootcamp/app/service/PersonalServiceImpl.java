package com.rafalazar.springboot.bootcamp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.springboot.bootcamp.app.documents.Personal;
import com.rafalazar.springboot.bootcamp.app.repository.PersonalRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalServiceImpl implements PersonalService{
	
	@Autowired
	private PersonalRepository personalRepo;

	@Override
	public Flux<Personal> selectAll() {
		return personalRepo.findAll();
	}

	@Override
	public Mono<Personal> selectById(String id) {
		return personalRepo.findById(id);
	}

	@Override
	public Mono<Personal> save(Personal personal) {
		return personalRepo.save(personal);
	}

	@Override
	public Mono<Void> delete(Personal personal) {
		return personalRepo.delete(personal);
	}

}
