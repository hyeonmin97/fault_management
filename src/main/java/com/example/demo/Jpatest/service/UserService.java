package com.example.demo.Jpatest.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Jpatest.domain.UserInfo;
import com.example.demo.Jpatest.dto.UserInfoDto;
import com.example.demo.Jpatest.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	private final UserInfoRepository userRepository;
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

