package com.rafalazar.springboot.bootcamp.app.documents;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="personal_client")
public class Personal {
	
	@Id
	private String id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String dni;
	@NotEmpty
	private String address;
	@NotEmpty
	@NotNull
	private String sex;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDay;
	private Account account;
	
	public Personal() {
		
	}
	
	public Personal(String name, String lastName, String dni, String address, String sex,Account account) {
		this.name = name;
		this.lastName = lastName;
		this.dni = dni;
		this.address = address;
		this.sex = sex;
		this.account = account;
	}

}
