package com.example.demo.Jpatest.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Test {
	private @Id String id;
	private String email;
	
}
