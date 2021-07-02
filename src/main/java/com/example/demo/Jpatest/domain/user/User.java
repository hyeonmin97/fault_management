package com.example.demo.Jpatest.domain.user;

import javax.persistence.*;

import com.example.demo.Jpatest.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //매개변수 없는 생성자
@Entity
public class User extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//자동 생성, 데이터베이스에 위임
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column
	private String picture;
	
	
	@Enumerated(EnumType.STRING)//열거형을 문자열 형식으로 저장
	@Column(nullable = false)
	private Role role;

	@Builder
	public User(String name, String email, String picture, Role role) {
		super();
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
		return this.role.getKey();
	}
	
	
	
}
