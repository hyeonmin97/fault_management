package com.example.demo.Jpatest.domain.oauth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.Jpatest.domain.BaseTimeEntity;
import com.example.demo.Jpatest.domain.UserRole;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private String picture;
	
	@Enumerated(EnumType.STRING)//문자열으로 저장되게
	@Column
	private UserRole role;
	
	@Builder
	public User(String name, String email, String picture, UserRole role) {
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.role = role;
	}
	
	public User update(String name, String picture) {
		this.name = name;
		this.picture = picture;
		
		return this;
	}
	
	public String getRoleKey() {
		return this.role.getValue();
	}
	
	
	
	
	//https://velog.io/@swchoi0329/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0%EC%99%80-OAuth-2.0%EC%9C%BC%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84#4-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98-%EA%B8%B0%EB%B0%98%EC%9C%BC%EB%A1%9C-%EA%B0%9C%EC%84%A0%ED%95%98%EA%B8%B0
}
