package com.example.demo.Jpatest.dto;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;

import com.example.demo.Jpatest.domain.oauth.User;

import lombok.Getter;


//직렬화를 구현해야한다는 오류가 나기때문에 생성한 클래스임
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
