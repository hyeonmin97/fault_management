package com.example.demo.Jpatest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum UserRole {
	USER("ROLE_USER", "일반 사용자"), 
	ADMIN("ROLE_ADMIN", "관리자");
	
	private String value;
	private String title;
}
