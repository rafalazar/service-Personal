package com.rafalazar.springboot.bootcamp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.springboot.bootcamp.app.documents.Account;
import com.rafalazar.springboot.bootcamp.app.documents.Personal;
import com.rafalazar.springboot.bootcamp.app.repository.AccountRepository;
import com.rafalazar.springboot.bootcamp.app.repository.PersonalRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalServiceImpl implements PersonalService{
	
	@Autowired
	private PersonalRepository personalRepo;
	
	@Autowired
	private AccountRepository accountRepo;

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

	@Override
	public Mono<Personal> update(Personal personal, String id) {
		return null;
	}

	// Accounts ----------------------------- //
	
	@Override
	public Flux<Account> findAllAccounts() {
		return accountRepo.findAll();
	}

	@Override
	public Mono<Account> findAccoundById(String id) {
		return accountRepo.findById(id);
	}

	@Override
	public Mono<Account> saveAccount(Account account) {
		return accountRepo.save(account);
	}

	// ---------------------------------------- //
	@Override
	public Mono<Personal> findByLastName(String lastName) {
		return personalRepo.findByLastName(lastName);
	}

	@Override
	public Mono<Personal> findByDni(String dni) {
		return personalRepo.findByDni(dni);
	}

}
