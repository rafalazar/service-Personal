package com.rafalazar.springboot.bootcamp.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.rafalazar.springboot.bootcamp.app.service.PersonalService;

@EnableEurekaClient
@SpringBootApplication
public class ServicePersonalApplication implements CommandLineRunner{
	
	@Autowired
	private PersonalService service;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(ServicePersonalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServicePersonalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
