package com.example.demo.Jpatest.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserInfoDto {
	private Long id;
	private String email;
	private String password;
	private String auth;
	
	
	@Builder
	public UserInfoDto(Long id, String email, String password, String auth) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.auth = auth;
	}
}
