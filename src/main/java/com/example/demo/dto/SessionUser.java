package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.oauth.User;

import lombok.Getter;


@Getter
public class SessionUser implements Serializable {
	
	private static final long serialVersionUID = 4212624117049361461L;
	private String name;
	private String email;
	private String picture;
	

	public SessionUser(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
}
