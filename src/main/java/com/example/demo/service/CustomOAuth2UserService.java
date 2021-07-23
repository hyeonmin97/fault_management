package com.example.demo.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.domain.oauth.User;
import com.example.demo.dto.OAuthAttributes;
import com.example.demo.dto.SessionUser;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	
	private final UserRepository userRepository;
	private final HttpSession httpSession;
	
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		
		
		
		//OAuth2UserService를 통해 가져온 OAuth2User의 attribute값을 저장할 클래스
		OAuthAttributes attributes = OAuthAttributes.
				of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
		//세션에 사용자 정보를 저장하기 위한 dto클래스
		User user = saveOrUpdate(attributes);
		
		httpSession.setAttribute("user", new SessionUser(user));
		
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
					attributes.getAttributes(),
					attributes.getNameAttributeKey());
	}
	
	//정보 저장하거나 업데이트
	private User saveOrUpdate(OAuthAttributes attributes) {
		User user = userRepository.findByEmail(attributes.getEmail())
				.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
				.orElse(attributes.toEntity());
		return userRepository.save(user);
	}
}
