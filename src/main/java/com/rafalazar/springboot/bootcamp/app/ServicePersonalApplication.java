package com.rafalazar.springboot.bootcamp.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.rafalazar.springboot.bootcamp.app.documents.Account;
import com.rafalazar.springboot.bootcamp.app.documents.Personal;
import com.rafalazar.springboot.bootcamp.app.service.PersonalService;

import reactor.core.publisher.Flux;

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
		mongoTemplate.dropCollection("account").subscribe();
		mongoTemplate.dropCollection("personal_client").subscribe();
		
		Account corriente = new Account("100597","Cuenta Corriente");
		Account ahorro = new Account("2007025","Cuenta de Ahorro");
		Account fijo = new Account("4342554","Cuenta a Plazo Fijo");
		
		Flux.just(corriente,ahorro,fijo).flatMap(a -> service.saveAccount(a)).doOnNext(a ->{
			log.info("Cuentas creadas: " + a.getId() + " - " + a.getNameAccount());
		}).thenMany(
				Flux.just(
						new Personal("Jordan","Salazar","72915854","Av. Las casuarinas","M",corriente),
						new Personal("Isabella","Sermon","79843212","U.K. - London","F",ahorro),
						new Personal("Robb","Stark","54623454","Winterfell","M",fijo))
				.flatMap(personal -> {
					personal.setBirthDay(new Date());
					return service.save(personal);
				}))
		.subscribe(personal -> log.info("Insertando: " + personal.getId() + " - " + personal.getName()));
		
		
		
	}

}