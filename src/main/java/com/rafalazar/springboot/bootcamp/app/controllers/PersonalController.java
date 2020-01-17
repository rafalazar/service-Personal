package com.rafalazar.springboot.bootcamp.app.controllers;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafalazar.springboot.bootcamp.app.documents.Personal;
import com.rafalazar.springboot.bootcamp.app.service.PersonalService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/personal_clients")
public class PersonalController {
	
	@Autowired
	private PersonalService service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Personal>>> list(){
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.selectAll()));
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Personal>> searchById(@PathVariable String id){
		return service.selectById(id).map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Mono<ResponseEntity<Personal>> create(@RequestBody Personal personal){
		if(personal.getBirthDay() == null) {
			personal.setBirthDay(new Date());
		}
		
		return service.save(personal).map(p -> ResponseEntity
				.created(URI.create("/api/personal_clients/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p)
				);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Personal>> edit(@RequestBody Personal personal, @PathVariable String id){
		return service.selectById(id).flatMap(p -> {
			p.setName(personal.getName());
			p.setLastName(personal.getLastName());
			p.setDni(personal.getDni());
			p.setAddress(personal.getAddress());
			p.setSex(personal.getSex());
			p.setBirthDay(personal.getBirthDay());
			p.setAccount(personal.getAccount());
			return service.save(p);
		}).map(p -> ResponseEntity.created(URI.create("/api/personal_clients/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(p)).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
		return service.selectById(id).flatMap(p -> {
			return service.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	//Buscamos por el DNI
	@GetMapping("/dni/{dni}")
	public Mono<ResponseEntity<Personal>> findByNumDni(@PathVariable String dni){
		return service.findByDni(dni).map(d -> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON).body(d))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
