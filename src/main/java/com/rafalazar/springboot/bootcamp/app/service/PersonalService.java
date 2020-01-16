package com.rafalazar.springboot.bootcamp.app.service;

import com.rafalazar.springboot.bootcamp.app.documents.Personal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalService {
	
	public Flux<Personal> selectAll();
	
	public Mono<Personal> selectById(String id);
	
	public Mono<Personal> save(Personal personal);
	
	public Mono<Void> delete(Personal personal);

}
