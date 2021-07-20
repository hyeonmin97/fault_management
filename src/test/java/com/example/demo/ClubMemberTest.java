package com.example.demo;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Jpatest.domain.UserInfo;
import com.example.demo.Jpatest.repository.UserInfoRepository;

//@SpringBootTest
public class ClubMemberTest {

	@Autowired
	private UserInfoRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Test
	public void insertDummies()
	{
		IntStream.rangeClosed(1,10).forEach(i ->{
			UserInfo clubMember = UserInfo.builder()
				.email("user" + i + "@zerock.org")
				.password(passwordEncoder.encode("1111"))
				.build();
			
			
			repository.save(clubMember);
		});
	}
	

	
}
