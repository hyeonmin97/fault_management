package com.example.demo.Jpatest.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	GUEST("ROLE_GUEST", "�մ�"),//����� �ٸ� ���� ����
	USER("ROLE_USER", "�Ϲ� �����");
	
	private final String key;
	private final String title;
}
