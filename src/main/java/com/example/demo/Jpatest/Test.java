package com.example.demo.Jpatest;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Test {
	private @Id String id;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
