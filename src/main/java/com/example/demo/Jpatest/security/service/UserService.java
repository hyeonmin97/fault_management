package com.example.demo.Jpatest.security.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Jpatest.domain.UserInfo;
import com.example.demo.Jpatest.repository.UserRepository;
import com.example.demo.Jpatest.security.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	private final UserRepository userRepository;
	@Override
	public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email)
					.orElseThrow(()->new UsernameNotFoundException((email)));
	}


	public Long save(UserInfoDto infoDto) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		infoDto.setPassword(encoder.encode(infoDto.getPassword()));
		
		return userRepository.save(UserInfo.builder()
				.email(infoDto.getEmail())
				.auth(infoDto.getAuth())
				.password(infoDto.getPassword()).build()).getId();
	}
}
