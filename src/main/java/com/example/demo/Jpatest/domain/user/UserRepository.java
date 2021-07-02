package com.example.demo.Jpatest.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	//Optional - WrapperŬ����
	Optional<User> findByEmail(String email);//�̸����� �̿��� ó�� �����ϴ� ��������� Ȯ��
}
