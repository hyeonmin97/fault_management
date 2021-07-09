package com.example.demo.Jpatest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum UserRole {
	USER("ROLE_USER", "�Ϲ� �����"), 
	ADMIN("ROLE_ADMIN", "������");
	
	private String value;
	private String title;
}
