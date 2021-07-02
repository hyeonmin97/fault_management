package com.example.demo.Jpatest.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	GUEST("ROLE_GUEST", "손님"),//상수를 다른 값과 연결
	USER("ROLE_USER", "일반 사용자");
	
	private final String key;
	private final String title;
}
