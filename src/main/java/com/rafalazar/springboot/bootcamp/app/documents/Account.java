package com.rafalazar.springboot.bootcamp.app.documents;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
//@Document(collection="personal-client")
public class Account {
	
	private String id;
	private String numberAccount;
	private String nameAccount;
	
	public Account(String numberAccount, String nameAccount) {
		this.numberAccount = numberAccount;
		this.nameAccount = nameAccount;
	}
	
	public Account() {
		
	}

}
