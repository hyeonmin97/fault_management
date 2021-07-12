package com.example.demo.Jpatest.dto;

import java.util.Map;


import com.example.demo.Jpatest.domain.UserRole;
import com.example.demo.Jpatest.domain.oauth.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
			String picture) {
		super();
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	//OAuth2User에서 반환하는 정보는 Map형태이기때문에 변환 필요
	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		return ofGoogle(userNameAttributeName, attributes);
	}
	
	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.name((String) attributes.get("name"))
				.email((String) attributes.get("email"))
				.picture((String) attributes.get("picture"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}
	
	public User toEntity() {
		return User.builder()
				.name(name)
				.email(email)
				.picture(picture)
				.role(UserRole.USER)
				.build();
	}
	
	
}

