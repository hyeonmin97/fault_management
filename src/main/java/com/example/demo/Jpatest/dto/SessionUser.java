package com.example.demo.Jpatest.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;

import com.example.demo.Jpatest.domain.oauth.User;

import lombok.Getter;


//이거 안쓰고 userinfdto로 세션관리하면 직렬화하라고 오류남
@Getter
public class SessionUser implements Serializable {
	
	private String name;
	private String email;
	private String picture;
	

	public SessionUser(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
}
