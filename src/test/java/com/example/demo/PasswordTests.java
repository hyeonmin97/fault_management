package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

//@SpringBootTest
public class PasswordTests {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testEncode() {
		String password = "1111";
		String enPw = passwordEncoder.encode(password);
		System.out.println("enpw:" + enPw);
		boolean matchResult = passwordEncoder.matches(password,enPw);
		System.out.println("match : " + matchResult);
	}
	
}
