package com.example.demo.Jpatest.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo extends BaseTimeEntity implements UserDetails{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email", unique=true)
	private String email;

	@Column(name="password")
	private String password;
	
	@Column(name = "auth")
	private String auth;
	
	@Builder
	public UserInfo(String email, String password, String auth) {
		this.email=email;
		this.password=password;
		this.auth=auth;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){//사용자 권한 컬렉션 형태로 리턴
		Set<GrantedAuthority> roles = new HashSet<>();
		for(String role : auth.split(",")) {//어드민 권한에는 유저권한 포함
			roles.add(new SimpleGrantedAuthority(role));
		}
		return roles;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
