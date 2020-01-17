package com.rafalazar.springboot.bootcamp.app.service;

import com.rafalazar.springboot.bootcamp.app.documents.Account;
import com.rafalazar.springboot.bootcamp.app.documents.Personal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonalService {
	
	//Lista
	public Flux<Personal> selectAll();
	//ListaPorId
	public Mono<Personal> selectById(String id);
	//Guarda
	public Mono<Personal> save(Personal personal);
	//Actualiza
	public Mono<Personal> update(Personal personal, String id);
	//Elimina
	public Mono<Void> delete(Personal personal);
	
	// ------------------------------------------------ //
	//Accounts Personal //
	public Flux<Account> findAllAccounts();
	public Mono<Account> findAccoundById(String id);
	public Mono<Account> saveAccount(Account account);
	
	// --------------------------------------------- //
	public Mono<Personal> findByLastName(String name);
	public Mono<Personal> findByDni(String dni);
}
