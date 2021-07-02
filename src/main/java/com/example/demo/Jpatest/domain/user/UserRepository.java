package com.example.demo.Jpatest.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	//Optional - Wrapper클래스
	Optional<User> findByEmail(String email);//이메일을 이용해 처음 가입하는 사용자인지 확인
}
